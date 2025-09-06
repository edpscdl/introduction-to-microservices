package by.gvu.resource.client;

import by.gvu.resource.model.dto.Mp3FileMetadataDto;
import by.gvu.resource.model.dto.Mp3FileMetadataResponce;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "song-service", url = "http://localhost:8081", configuration = FeignConfiguration.class)
public interface SongServiceClient {

    @PostMapping("/songs")
    Mp3FileMetadataResponce createSong(@RequestBody Mp3FileMetadataDto mp3FileMetadataDto);
}
