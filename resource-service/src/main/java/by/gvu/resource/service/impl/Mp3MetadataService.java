package by.gvu.resource.service.impl;

import by.gvu.resource.dto.Mp3FileMetadataRequestDto;
import by.gvu.resource.exception.ResourceServiceParseMetadataException;
import by.gvu.resource.model.Mp3FileModel;
import by.gvu.resource.service.MetadataService;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class Mp3MetadataService implements MetadataService<Mp3FileModel, Mp3FileMetadataRequestDto> {
    @Override
    public Mp3FileMetadataRequestDto readMetadata(Mp3FileModel mp3FileModel) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(mp3FileModel.getData())) {
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseContext = new ParseContext();

            parser.parse(inputStream, handler, metadata, parseContext);

            return Mp3FileMetadataRequestDto.builder()
                    .id(mp3FileModel.getId())
                    .name(metadata.get("dc:title"))
                    .artist(metadata.get("xmpDM:artist"))
                    .album(metadata.get("xmpDM:album"))
                    .duration(convertStringDoubleToDuration(metadata.get("xmpDM:duration")))
                    .year(convertStringToYear(metadata.get("xmpDM:releaseDate")))
                    .build();
        } catch (Exception e) {
            throw new ResourceServiceParseMetadataException("Failed to read metadata");
        }
    }

    private String convertStringDoubleToDuration(String duration) {
        double totalSeconds = Double.parseDouble(duration);
        long minutes = (long) totalSeconds / 60;
        long seconds = Math.round(totalSeconds % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    private Integer convertStringToYear(String year) {
        return Integer.parseInt(year);
    }
}
