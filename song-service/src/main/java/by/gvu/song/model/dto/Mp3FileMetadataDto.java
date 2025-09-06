package by.gvu.song.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Duration;

@Data
public class Mp3FileMetadataDto {
    @NotNull(message = "Id is required")
    @Positive(message = "Id must be a positive number")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be 1-100 characters long")
    private String name;

    @NotBlank(message = "Artist is required")
    @Size(min = 1, max = 100, message = "Artist must be 1-100 characters long")
    private String artist;

    @NotBlank(message = "Album is required")
    @Size(min = 1, max = 100, message = "Album must be 1-100 characters long")
    private String album;

    @NotBlank(message = "Duration is required")
    @Pattern(
            regexp = "^([0-5][0-9]):([0-5][0-9])$",
            message = "Duration must be in the format mm:ss with leading zeros"
    )
    private String duration;

    @NotNull(message = "Year is required")
    @Size(min = 4, max = 4, message = "Year must be exactly 4 characters")
    @Pattern(regexp = "^(19[0-9]{2}|20[0-9]{2})$", message = "Year must be between 1900 and 2099")
    private String year;
}
