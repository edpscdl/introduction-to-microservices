package by.gvu.song.service.impl;

import by.gvu.song.exception.SongServiceDuplicateSourceException;
import by.gvu.song.model.Mp3FileMetadataModel;
import by.gvu.song.repository.SongFileMetadataRepository;
import by.gvu.song.service.SongFileMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Mp3SongFileMetadataService implements SongFileMetadataService<Mp3FileMetadataModel, Long> {
    private final SongFileMetadataRepository repository;


    @Override
    @Transactional
    public Long create(Mp3FileMetadataModel metadata) {
        if (repository.existsById(metadata.getId())) {
            throw new SongServiceDuplicateSourceException("Duplicate source ["+metadata.getId()+"]");
        }

        Mp3FileMetadataModel savedMatadata = repository.save(metadata);
        return savedMatadata.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mp3FileMetadataModel> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public List<Long> deleteByCsvIds(List<Long> ids) {
        List<Long> foundMetadataIds = repository.findAllById(ids).stream().map(Mp3FileMetadataModel::getId).toList();

        foundMetadataIds.forEach(repository::deleteById);

        return foundMetadataIds;
    }
}
