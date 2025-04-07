package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AdditionRequest {
    @JsonProperty("additionalInfo")
    private String additionalInfo;

    @JsonProperty("additionalNumber")
    private int additionalNumber;
}
