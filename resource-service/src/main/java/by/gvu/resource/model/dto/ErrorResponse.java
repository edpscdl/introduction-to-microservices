package by.gvu.resource.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ErrorResponse {
    private String errorMessage;
    private Integer errorCode;
    private Map<String, List<String>> details;
}
