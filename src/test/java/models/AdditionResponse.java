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
public class AdditionResponse {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("addition")
    private AdditionRequest addition;
}
