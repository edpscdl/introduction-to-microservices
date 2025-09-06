package by.gvu.song.mapper;

import by.gvu.song.model.data.Mp3FileMetadataModel;
import by.gvu.song.model.dto.Mp3FileMetadataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Duration;

@Mapper(componentModel = "spring")
public interface Mp3FileMetadataMapper {

    @Mapping(target = "duration", source = "duration", qualifiedByName = "convertStringToDuration")
    Mp3FileMetadataModel toModel(Mp3FileMetadataDto dto);

    @Mapping(target = "duration", source = "duration", qualifiedByName = "convertDurationToString")
    Mp3FileMetadataDto toDto(Mp3FileMetadataModel model);

    @Named("convertStringToDuration")
    default Duration convertStringToDuration(String durationString) {
        String[] parts = durationString.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return Duration.ofMinutes(minutes).plusSeconds(seconds);
    }

    @Named("convertDurationToString")
    default String convertDurationToString(Duration duration) {
        long totalSeconds = duration.getSeconds();
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
