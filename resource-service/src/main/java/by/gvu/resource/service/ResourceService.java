package by.gvu.resource.service;

import java.util.List;
import java.util.Optional;

public interface ResourceService<O, K> {
    O saveFileData(byte[] mp3FileData);
    Optional<O> getFileData(K fileDataId);
    void deleteById(K fileDataId);
    List<K> deleteByCsvIds(List<K> ids);
}
