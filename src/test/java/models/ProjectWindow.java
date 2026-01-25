package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectWindow {
    String projectName;
    String projectCode;
    String projectDescription;
}
