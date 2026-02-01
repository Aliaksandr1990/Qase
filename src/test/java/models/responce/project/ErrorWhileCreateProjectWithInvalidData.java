package models.responce.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorWhileCreateProjectWithInvalidData {
    private String status;
    private String errorMessage;
}
