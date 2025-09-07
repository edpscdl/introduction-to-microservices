package by.gvu.song.facade.impl;

import by.gvu.song.dto.Mp3FileMetadataRequestDto;
import by.gvu.song.dto.Mp3FileMetadataResponceDto;
import by.gvu.song.exception.SongServiceBaseException;
import by.gvu.song.exception.SongServiceMetadataNotFoundException;
import by.gvu.song.exception.SongServiceValidationException;
import by.gvu.song.facade.SongServiceFacade;
import by.gvu.song.model.Mp3FileMetadataModel;
import by.gvu.song.service.impl.Mp3SongFileMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Mp3MetadataSongServiceFacade implements SongServiceFacade<Mp3FileMetadataRequestDto, Mp3FileMetadataResponceDto, Long> {
    private final Mp3SongFileMetadataService songFileMetadataService;
    private final ConversionService conversionService;

    @Override
    public Long create(Mp3FileMetadataRequestDto metadataDto) {
        Mp3FileMetadataModel metadataModel = conversionService.convert(metadataDto, Mp3FileMetadataModel.class);
        return songFileMetadataService.create(metadataModel);
    }

    @Override
    public Mp3FileMetadataResponceDto getById(Long id) {
        Mp3FileMetadataModel metadataModel = songFileMetadataService.getById(id).orElseThrow(() -> new SongServiceMetadataNotFoundException("Metadata with id [" + id + "] not found"));
        return conversionService.convert(metadataModel, Mp3FileMetadataResponceDto.class);
    }

    @Override
    public List<Long> deleteByCsvIds(String csvIds) {
        List<Long> validIds = validateAndConvetStringIdsToListLong(csvIds);

        return songFileMetadataService.deleteByCsvIds(validIds);
    }

    private List<Long> validateAndConvetStringIdsToListLong(String ids) {
        if (ids == null || ids.isBlank()) {
            throw new SongServiceValidationException(Map.of("id", "is required"));
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
