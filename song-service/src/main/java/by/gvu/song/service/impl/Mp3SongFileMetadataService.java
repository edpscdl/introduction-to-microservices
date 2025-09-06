package by.gvu.song.service.impl;

import by.gvu.song.exception.SongServiceDuplicateSourceException;
import by.gvu.song.exception.SongServiceMetadataNotFoundException;
import by.gvu.song.model.data.Mp3FileMetadataModel;
import by.gvu.song.repository.SongFileMetadataRepository;
import by.gvu.song.service.SongFileMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Mp3SongFileMetadataService implements SongFileMetadataService {
    private final SongFileMetadataRepository repository;

    @Override
    public Mp3FileMetadataModel createMetadata(Mp3FileMetadataModel mp3FileMetadata) {
//        if (readMetadata(mp3FileMetadata.getId())!=null) {
//            throw new SongServiceDuplicateSourceException("Duplicate source ["+mp3FileMetadata.getId()+"]");
//        }
        return repository.save(mp3FileMetadata);
    }

    @Override
    public Mp3FileMetadataModel readMetadata(Long id) {
        return repository.findById(id).orElseThrow(() -> new SongServiceMetadataNotFoundException("Metadata with id " + id + " not found"));
    }

    @Override
    public Long deleteMetadataById(Long id) {
        Mp3FileMetadataModel metadata = repository.findById(id).orElseThrow(() -> new SongServiceMetadataNotFoundException("Metadata with id " + id + " not found"));
        repository.deleteById(metadata.getId());
        return metadata.getId();
    }
}
