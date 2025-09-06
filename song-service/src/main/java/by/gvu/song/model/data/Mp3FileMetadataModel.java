package by.gvu.song.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Duration;

@Entity
@Table(name = "mp3_file_metadata")
@Data
public class Mp3FileMetadataModel {
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String artist;

    @Column(nullable = false, length = 100)
    private String album;

    @Column(nullable = false)
    private Duration duration;

    @Column(nullable = false)
    private Integer year;
}
