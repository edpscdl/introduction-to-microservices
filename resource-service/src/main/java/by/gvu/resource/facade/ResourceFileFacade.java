package by.gvu.resource.facade;

import by.gvu.resource.exception.ResourceServiceValidationException;

import java.util.List;

public interface ResourceFileFacade<O, T> {
    O uploadFile(byte[] mp3FileData);

    byte[] downloadFile(String stringFileId) throws ResourceServiceValidationException;

    List<T> deleteFilesById(String stringListFileIds) throws ResourceServiceValidationException;
}
