package models.responce.project.get;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Defects{
    private int total;
    private int open;
}