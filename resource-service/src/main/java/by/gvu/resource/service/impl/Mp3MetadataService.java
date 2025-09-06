package by.gvu.resource.service.impl;

import by.gvu.resource.exception.ResourceServiceParseMetadataException;
import by.gvu.resource.model.data.Mp3FileModel;
import by.gvu.resource.model.dto.Mp3FileMetadataDto;
import by.gvu.resource.service.MetadataService;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.Duration;

@Service
public class Mp3MetadataService implements MetadataService<Mp3FileModel, Mp3FileMetadataDto> {
    @Override
    public Mp3FileMetadataDto readMetadata(Mp3FileModel mp3FileModel) throws ResourceServiceParseMetadataException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(mp3FileModel.getData())) {
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            Parser parser = new Mp3Parser();
            ParseContext parseContext = new ParseContext();

            parser.parse(inputStream, handler, metadata, parseContext);

            Mp3FileMetadataDto mp3FileMetadataDto = new Mp3FileMetadataDto();
            mp3FileMetadataDto.setId(mp3FileModel.getId());
            mp3FileMetadataDto.setName(metadata.get("dc:title"));
            mp3FileMetadataDto.setArtist(metadata.get("xmpDM:artist"));
            mp3FileMetadataDto.setAlbum(metadata.get("xmpDM:album"));
            mp3FileMetadataDto.setDuration(convertStringDoubleToDuration(metadata.get("xmpDM:duration")));
            mp3FileMetadataDto.setYear(convertStringToYear(metadata.get("xmpDM:releaseDate")));

            return mp3FileMetadataDto;
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
