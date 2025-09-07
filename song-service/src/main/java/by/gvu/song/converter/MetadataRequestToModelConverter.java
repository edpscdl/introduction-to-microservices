package by.gvu.song.converter;

import by.gvu.song.dto.Mp3FileMetadataRequestDto;
import by.gvu.song.model.Mp3FileMetadataModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MetadataRequestToModelConverter implements Converter<Mp3FileMetadataRequestDto, Mp3FileMetadataModel> {
    @Override
    public Mp3FileMetadataModel convert(Mp3FileMetadataRequestDto source) {
        return Mp3FileMetadataModel.builder()
                .id(source.getId())
                .name(source.getName())
                .artist(source.getArtist())
                .album(source.getAlbum())
                .duration(source.getDuration())
                .year(source.getYear())
                .build();
    }
}
