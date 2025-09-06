package by.gvu.song.controller;

import by.gvu.song.facade.SongFileMetadataFacade;
import by.gvu.song.model.dto.Mp3FileMetadataDto;
import by.gvu.song.model.dto.Mp3FileMetadataResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SongServiceController {
    private final SongFileMetadataFacade songFileMetadataFacade;

    @PostMapping(value = "/songs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mp3FileMetadataResponce> uploadMp3(@RequestBody @Validated Mp3FileMetadataDto mp3FileMetadata) {
        return ResponseEntity.ok(songFileMetadataFacade.createMetadata(mp3FileMetadata));
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Mp3FileMetadataDto> getMetadata(@PathVariable("id") String id) {
        return ResponseEntity.ok(songFileMetadataFacade.readMetadata(id));
    }

    @DeleteMapping("/songs")
    public ResponseEntity<Map<String, List<Long>>> deleteMp3(@RequestParam("id") String ids) {
        return ResponseEntity.ok(Map.of("ids", songFileMetadataFacade.deleteFilesById(ids)));
    }
}
