package by.gvu.song.repository;

import by.gvu.song.model.Mp3FileMetadataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongFileMetadataRepository extends JpaRepository<Mp3FileMetadataModel, Long> {
}
