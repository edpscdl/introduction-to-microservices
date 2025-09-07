package by.gvu.song.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mp3FileMetadataResponceDto {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private String year;
}