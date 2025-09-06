package by.gvu.resource.model.dto;

import lombok.Data;

import java.time.Duration;

@Data
public class Mp3FileMetadataDto {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private Integer year;
}
