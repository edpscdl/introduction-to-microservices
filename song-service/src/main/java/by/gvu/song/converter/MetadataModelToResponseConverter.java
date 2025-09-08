package by.gvu.song.converter;

import by.gvu.song.dto.Mp3FileMetadataResponseDto;
import by.gvu.song.model.Mp3FileMetadataModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MetadataModelToResponseConverter implements Converter<Mp3FileMetadataModel, Mp3FileMetadataResponseDto> {
    @Override
    public Mp3FileMetadataResponseDto convert(Mp3FileMetadataModel source) {
        return Mp3FileMetadataResponseDto.builder()
                .id(source.getId())
                .name(source.getName())
                .artist(source.getArtist())
                .album(source.getAlbum())
                .duration(source.getDuration())
                .year(source.getYear())
                .build();
    }
}
