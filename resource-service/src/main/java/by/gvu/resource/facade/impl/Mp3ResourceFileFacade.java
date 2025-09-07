package by.gvu.resource.facade.impl;

import by.gvu.resource.client.SongServiceClient;
import by.gvu.resource.dto.Mp3FileMetadataRequestDto;
import by.gvu.resource.exception.ResourceServiceBaseException;
import by.gvu.resource.exception.ResourceServiceFileNotFountException;
import by.gvu.resource.exception.ResourceServiceValidationException;
import by.gvu.resource.facade.ResourceFileFacade;
import by.gvu.resource.model.Mp3FileModel;
import by.gvu.resource.service.impl.Mp3MetadataService;
import by.gvu.resource.service.impl.Mp3ResourceService;
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
public class Mp3ResourceFileFacade implements ResourceFileFacade<Long> {
    private final Mp3ResourceService mp3ResourceService;
    private final Mp3MetadataService mp3MetadataService;
    private final SongServiceClient songServiceClient;

    @Override
    public Long uploadFile(byte[] mp3FileData) {

        Mp3FileModel createdMp3FileModel = null;
        Mp3FileMetadataRequestDto mp3FileMetadataRequestDto = null;

        try {
            createdMp3FileModel = mp3ResourceService.saveFileData(mp3FileData);

            mp3FileMetadataRequestDto = mp3MetadataService.readMetadata(createdMp3FileModel);

            ResponseEntity<Map<String, Long>> responseEntity = songServiceClient.create(mp3FileMetadataRequestDto);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody().get("id");
            } else {
                throw new ResourceServiceBaseException("Song service returned error code: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            if (createdMp3FileModel != null) {
                mp3ResourceService.deleteById(createdMp3FileModel.getId());

                if (mp3FileMetadataRequestDto != null) {
                    songServiceClient.delete(createdMp3FileModel.getId().toString());
                }
            }
            throw new ResourceServiceBaseException("Error saving song");
        }
    }

    @Override
    public byte[] downloadFile(String stringFileId) {
        return mp3ResourceService.getFileData(validateAndConvertStringIdToLong(stringFileId)).orElseThrow(() -> new ResourceServiceFileNotFountException("File ["+stringFileId+"] not found")).getData();
    }

    @Override
    public List<Long> deleteFilesById(String stringListFileIds) {

        List<Long> validIds = validateAndConvetListStringIdToListLong(stringListFileIds);

        try {
            ResponseEntity<Map<String, List<Long>>> deletedMetadataIds = songServiceClient.delete(validIds.stream().map(Object::toString).collect(Collectors.joining(",")));

            return mp3ResourceService.deleteByCsvIds(validIds);
        } catch (Exception e) {
            throw new ResourceServiceBaseException("Error deleting song");
        }
    }

    private Long validateAndConvertStringIdToLong(String id) {
        return validateAndConvetListStringIdToListLong(id).stream().findFirst().orElseThrow(() -> new ResourceServiceValidationException(Map.of("id", "value '" + id + "' is invalid")));
    }

    private List<Long> validateAndConvetListStringIdToListLong(String ids) {
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
