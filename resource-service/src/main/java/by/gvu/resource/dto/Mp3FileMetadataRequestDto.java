package by.gvu.resource.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mp3FileMetadataRequestDto {
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private Integer year;
}