package by.gvu.song.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "songs_metadata")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mp3FileMetadataModel {
    @Id
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String artist;
    @Column(nullable = false)
    private String album;
    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private String year;
}
