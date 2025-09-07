package by.gvu.resource.facade;

import java.util.List;

public interface ResourceFileFacade<I> {
    I uploadFile(byte[] mp3FileData);
    byte[] downloadFile(String stringFileId);
    List<I> deleteFilesById(String stringListFileIds);
}
