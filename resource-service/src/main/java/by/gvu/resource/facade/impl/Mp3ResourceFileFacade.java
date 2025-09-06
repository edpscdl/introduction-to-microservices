package by.gvu.resource.facade.impl;

import by.gvu.resource.client.SongServiceClient;
import by.gvu.resource.exception.ResourceServiceBaseException;
import by.gvu.resource.exception.ResourceServiceValidationException;
import by.gvu.resource.facade.ResourceFileFacade;
import by.gvu.resource.model.data.Mp3FileModel;
import by.gvu.resource.model.dto.Mp3FileMetadataDto;
import by.gvu.resource.model.dto.Mp3FileMetadataResponce;
import by.gvu.resource.model.dto.Mp3FileResponce;
import by.gvu.resource.service.impl.Mp3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Mp3ResourceFileFacade implements ResourceFileFacade<Mp3FileResponce, Long> {
    private final Mp3FileService fileService;
    private final SongServiceClient songServiceClient;

    @Override
    public Mp3FileResponce uploadFile(byte[] mp3FileData) {

        Mp3FileModel mp3FileModel = null;
        Mp3FileMetadataDto mp3FileMetadataDto = null;

        try {
            mp3FileModel = fileService.saveFile(mp3FileData);
            mp3FileMetadataDto = fileService.readMetadata(mp3FileModel.getId());

            Mp3FileMetadataResponce metadataResponce = songServiceClient.createSong(mp3FileMetadataDto);

            if (metadataResponce.id().equals(mp3FileModel.getId())) {
                return new Mp3FileResponce(mp3FileModel.getId());
            } else {
                throw new ResourceServiceBaseException("Song id is not equal to file id");
            }
        } catch (Exception e) {
            if (mp3FileMetadataDto != null) {
                songServiceClient.deleteMp3(mp3FileModel.getId().toString());
            }

            if (mp3FileModel != null) {
                fileService.deleteFilesById(Collections.singletonList(mp3FileModel.getId()));
            }
            throw e;
        }
    }

    @Override
    public byte[] downloadFile(String stringFileId) throws ResourceServiceValidationException {
        return fileService.getFile(validateAndConvertStringIdToLong(stringFileId));
    }

    @Override
    public List<Long> deleteFilesById(String stringListFileIds) throws ResourceServiceValidationException {
        List<Long> deletedFileIds = fileService.deleteFilesById(validateAndConvetStringIdsToListLong(stringListFileIds));

        List<ResponseEntity<Map<String, Long>>> deletedMetadataIds = deletedFileIds.stream().map(id -> songServiceClient.deleteMp3(id.toString())).toList();

        return deletedFileIds;
    }

    private Long validateAndConvertStringIdToLong(String id) throws ResourceServiceValidationException {
        return validateAndConvetStringIdsToListLong(id).stream().findFirst().orElseThrow(() -> new ResourceServiceValidationException(Map.of("id", "id cannot be empty")));
    }

    private List<Long> validateAndConvetStringIdsToListLong(String ids) throws ResourceServiceValidationException {
        if (ids == null) {
            return Collections.emptyList();
        }

        if (ids.length() > 200) {
            throw new ResourceServiceBaseException("The length of parameter id cannot be more than 200. current '" + ids.length() + "'");
        }

        List<String> invalidValues = new ArrayList<>();

        List<Long> resultIds = Arrays.stream(ids.split(",")).map((stringId) -> {
            try {
                long id = Long.parseLong(stringId);
                if (id < 1) {
                    invalidValues.add(stringId);
                    return null;
                }
                return Long.parseLong(stringId);
            } catch (NumberFormatException e) {
                invalidValues.add(stringId);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        if (!invalidValues.isEmpty()) {
            throw new ResourceServiceValidationException(Map.of("id", "Invalid values: " + String.join(",", invalidValues)));
        }

        return resultIds;
    }
}
