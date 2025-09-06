package by.gvu.song.service;

import by.gvu.song.model.data.Mp3FileMetadataModel;

import java.util.List;

public interface SongFileMetadataService {
    Mp3FileMetadataModel createMetadata(Mp3FileMetadataModel mp3FileMetadata);
    Mp3FileMetadataModel readMetadata(Long id);
    Long deleteMetadataById(Long id);
}
