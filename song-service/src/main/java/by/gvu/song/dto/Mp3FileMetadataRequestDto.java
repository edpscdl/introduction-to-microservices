package by.gvu.song.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mp3FileMetadataRequestDto {
    @NotNull(message = "id is required")
    @Positive(message = "id must be positive")
    private Long id;

    @NotBlank(message = "name is required")
    @Size(min = 1, max = 100, message = "name length must be between 1 and 100")
    private String name;

    @NotBlank(message = "artist is required")
    @Size(min = 1, max = 100, message = "artist length must be between 1 and 100")
    private String artist;

    @NotBlank(message = "album is required")
    @Size(min = 1, max = 100, message = "album length must be between 1 and 100")
    private String album;

    @NotBlank(message = "duration is required")
    @Pattern(regexp = "^(0[0-9]|[1-5][0-9]):[0-5][0-9]$", message = "duration must match mm:ss with leading zeros")
    private String duration;

    @NotBlank(message = "year is required")
    @Pattern(regexp = "^(19\\d{2}|20\\d{2})$", message = "year must be YYYY between 1900-2099")
    private String year;
}