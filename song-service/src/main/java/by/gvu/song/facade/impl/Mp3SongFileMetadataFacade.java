package by.gvu.song.facade.impl;

import by.gvu.song.exception.SongServiceBaseException;
import by.gvu.song.exception.SongServiceValidationException;
import by.gvu.song.facade.SongFileMetadataFacade;
import by.gvu.song.mapper.Mp3FileMetadataMapper;
import by.gvu.song.model.data.Mp3FileMetadataModel;
import by.gvu.song.model.dto.Mp3FileMetadataDto;
import by.gvu.song.model.dto.Mp3FileMetadataResponce;
import by.gvu.song.service.SongFileMetadataService;
import lombok.RequiredArgsConstructor;
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
public class Mp3SongFileMetadataFacade implements SongFileMetadataFacade {
    private final Mp3FileMetadataMapper mp3FileMetadataMapper;
    private final SongFileMetadataService songFileMetadataService;

    @Override
    public Mp3FileMetadataResponce createMetadata(Mp3FileMetadataDto mp3FileMetadata) {
        Mp3FileMetadataModel createdMp3FileMetadataModel = songFileMetadataService.createMetadata(mp3FileMetadataMapper.toModel(mp3FileMetadata));

        if (createdMp3FileMetadataModel != null) {
            return new Mp3FileMetadataResponce(createdMp3FileMetadataModel.getId());
        }

        return null;
    }

    @Override
    public Mp3FileMetadataDto readMetadata(String id) {
        return mp3FileMetadataMapper.toDto(songFileMetadataService.readMetadata(validateAndConvertStringIdToLong(id)));
    }

    @Override
    public List<Long> deleteFilesById(String stringListFileIds) throws SongServiceValidationException {
        return songFileMetadataService.deleteFilesById(validateAndConvetStringIdsToListLong(stringListFileIds));
    }

    private Long validateAndConvertStringIdToLong(String id) throws SongServiceValidationException {
        return validateAndConvetStringIdsToListLong(id).stream().findFirst().orElseThrow(() -> new SongServiceValidationException(Map.of("id", "id cannot be empty")));
    }

    private List<Long> validateAndConvetStringIdsToListLong(String ids) throws SongServiceValidationException {
        if (ids == null) {
            return Collections.emptyList();
        }

        if (ids.length() > 200) {
            throw new SongServiceBaseException("The length of parameter id cannot be more than 200. current '" + ids.length() + "'");
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
            throw new SongServiceValidationException(Map.of("id", "Invalid values: " + String.join(",", invalidValues)));
        }

        return resultIds;
    }
}
