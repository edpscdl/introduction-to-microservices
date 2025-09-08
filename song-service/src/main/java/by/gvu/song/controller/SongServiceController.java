package by.gvu.song.controller;

import by.gvu.song.dto.Mp3FileMetadataRequestDto;
import by.gvu.song.dto.Mp3FileMetadataResponseDto;
import by.gvu.song.facade.impl.Mp3MetadataSongServiceFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongServiceController {
    private final Mp3MetadataSongServiceFacade mp3MetadataSongServiceFacade;

    @PostMapping
    public ResponseEntity<Map<String, Long>> create(@Valid @RequestBody Mp3FileMetadataRequestDto mp3FileMetadataRequestDto) {
        return ResponseEntity.ok(Map.of("id", mp3MetadataSongServiceFacade.create(mp3FileMetadataRequestDto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mp3FileMetadataResponseDto> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(mp3MetadataSongServiceFacade.getById(id));
    }

    @DeleteMapping
    public ResponseEntity<Map<String, List<Long>>> delete(@RequestParam("id") String csvIds) {
        return ResponseEntity.ok(Map.of("ids", mp3MetadataSongServiceFacade.deleteByCsvIds(csvIds)));
    }
}
