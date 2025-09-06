package by.gvu.song.facade;

import by.gvu.song.model.dto.Mp3FileMetadataDto;
import by.gvu.song.model.dto.Mp3FileMetadataResponce;

import java.util.List;

public interface SongFileMetadataFacade {
    Mp3FileMetadataResponce createMetadata(Mp3FileMetadataDto mp3FileMetadata);
    Mp3FileMetadataDto readMetadata(String id);

    List<Long> deleteFilesById(String ids);
}
