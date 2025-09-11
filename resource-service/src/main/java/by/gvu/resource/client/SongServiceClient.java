package by.gvu.resource.client;

import by.gvu.resource.dto.Mp3FileMetadataRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "song-service", url = "${rest.song_endpoint}", configuration = FeignConfiguration.class)
public interface SongServiceClient {

    @PostMapping
    ResponseEntity<Map<String, Long>> create(@RequestBody Mp3FileMetadataRequestDto mp3FileMetadataRequestDto);

    @DeleteMapping
    ResponseEntity<Map<String, List<Long>>> delete(@RequestParam("id") String csvIds);
}
