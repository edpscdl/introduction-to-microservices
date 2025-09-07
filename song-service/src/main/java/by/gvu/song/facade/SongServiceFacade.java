package by.gvu.song.facade;

import java.util.List;

public interface SongServiceFacade<I, O, K> {
    K create(I metadataDto);
    O getById(K id);
    List<K> deleteByCsvIds(String csvIds);
}
