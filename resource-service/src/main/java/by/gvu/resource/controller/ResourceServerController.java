package by.gvu.resource.controller;

import by.gvu.resource.facade.impl.Mp3ResourceFileFacade;
import by.gvu.resource.model.dto.Mp3FileResponce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class ResourceServerController {
    private final Mp3ResourceFileFacade mp3ResourceFileFacade;

    @PostMapping(value = "/resources", consumes = "audio/mpeg", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mp3FileResponce> uploadMp3(@RequestBody byte[] fileBytes) {
        return ResponseEntity.ok(mp3ResourceFileFacade.uploadFile(fileBytes));
    }

    @GetMapping(value = "/resources/{id}", produces = "audio/mpeg")
    public ResponseEntity<byte[]> getMp3(@PathVariable("id") String id) {
        return ResponseEntity.ok(mp3ResourceFileFacade.downloadFile(id));
    }

    @DeleteMapping("/resources")
    public ResponseEntity<Map<String, List<Long>>> deleteMp3(@RequestParam("id") String ids) {
        return ResponseEntity.ok(Map.of("ids", mp3ResourceFileFacade.deleteFilesById(ids)));
    }
}
