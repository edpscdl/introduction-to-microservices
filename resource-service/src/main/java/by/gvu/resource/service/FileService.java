package by.gvu.resource.service;

import by.gvu.resource.exception.ResourceServiceFileNotFountException;
import by.gvu.resource.exception.ResourceServiceParseMetadataException;

import java.util.List;

public interface FileService<I, M, O> {
    O saveFile(byte[] mp3FileData);

    M readMetadata(I fileId) throws ResourceServiceFileNotFountException, ResourceServiceParseMetadataException;

    byte[] getFile(I fileId) throws ResourceServiceFileNotFountException;

    List<I> deleteFilesById(List<I> fileIds);
}
