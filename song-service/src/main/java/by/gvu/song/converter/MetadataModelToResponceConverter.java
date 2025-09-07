package by.gvu.song.converter;

import by.gvu.song.dto.Mp3FileMetadataResponceDto;
import by.gvu.song.model.Mp3FileMetadataModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MetadataModelToResponceConverter implements Converter<Mp3FileMetadataModel, Mp3FileMetadataResponceDto> {
    @Override
    public Mp3FileMetadataResponceDto convert(Mp3FileMetadataModel source) {
        return Mp3FileMetadataResponceDto.builder()
                .id(source.getId())
                .name(source.getName())
                .artist(source.getArtist())
                .album(source.getAlbum())
                .duration(source.getDuration())
                .year(source.getYear())
                .build();
    }
}
