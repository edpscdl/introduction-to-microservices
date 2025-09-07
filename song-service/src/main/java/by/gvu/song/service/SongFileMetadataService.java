package by.gvu.song.service;

import java.util.List;
import java.util.Optional;

public interface SongFileMetadataService<S, I> {
    I create(S metadata);
    Optional<S> getById(I id);
    List<I> deleteByCsvIds(List<I> ids);
}
