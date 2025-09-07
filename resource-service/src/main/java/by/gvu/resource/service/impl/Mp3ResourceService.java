package by.gvu.resource.service.impl;

import by.gvu.resource.model.Mp3FileModel;
import by.gvu.resource.repository.Mp3FileRepository;
import by.gvu.resource.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Mp3ResourceService implements ResourceService<Mp3FileModel, Long> {
    private final Mp3FileRepository repository;

    public Mp3FileModel saveFileData(byte[] mp3FileData) {
        return repository.save(Mp3FileModel.builder().data(mp3FileData).build());
    }

    @Override
    public Optional<Mp3FileModel> getFileData(Long fileDataId) {
        return repository.findById(fileDataId);
    }

    @Override
    public void deleteById(Long fileDataId) {
        repository.deleteById(fileDataId);
    }

    @Override
    public List<Long> deleteByCsvIds(List<Long> ids) {
        List<Long> avaibleListFileData = repository.findAllById(ids).stream().map(Mp3FileModel::getId).toList();

        repository.deleteAllById(avaibleListFileData);

        return avaibleListFileData;
    }
}
