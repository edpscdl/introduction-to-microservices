package by.gvu.resource.service.impl;

import by.gvu.resource.exception.ResourceServiceFileNotFountException;
import by.gvu.resource.exception.ResourceServiceParseMetadataException;
import by.gvu.resource.model.data.Mp3FileModel;
import by.gvu.resource.model.dto.Mp3FileMetadataDto;
import by.gvu.resource.repository.Mp3FileRepository;
import by.gvu.resource.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Mp3FileService implements FileService<Long, Mp3FileMetadataDto, Mp3FileModel> {
    private final Mp3FileRepository repository;
    private final Mp3MetadataService mp3MetadataService;

    @Override
    public Mp3FileModel saveFile(byte[] mp3FileData) {
        Mp3FileModel mp3FileModel = new Mp3FileModel();
        mp3FileModel.setData(mp3FileData);

        return repository.save(mp3FileModel);
    }

    @Override
    public Mp3FileMetadataDto readMetadata(Long fileId) throws ResourceServiceFileNotFountException, ResourceServiceParseMetadataException {
        Mp3FileModel mp3FileModel = repository.findById(fileId).orElseThrow(() -> new ResourceServiceFileNotFountException("File with id " + fileId + " not found"));
        return mp3MetadataService.readMetadata(mp3FileModel);
    }

    @Override
    public byte[] getFile(Long fileId) throws ResourceServiceFileNotFountException {
        return repository.findById(fileId).map(Mp3FileModel::getData).orElseThrow(() -> new ResourceServiceFileNotFountException("File with id " + fileId + " not found"));
    }

    @Override
    public List<Long> deleteFilesById(List<Long> fileIds) {
        List<Long> avaibleFileIds = repository.findAllById(fileIds).stream().map(Mp3FileModel::getId).toList();
        repository.deleteAllById(avaibleFileIds);
        return avaibleFileIds;
    }
}
