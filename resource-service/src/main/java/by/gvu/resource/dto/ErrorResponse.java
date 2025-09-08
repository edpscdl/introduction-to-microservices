package by.gvu.resource.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, List<String>> details;
}
