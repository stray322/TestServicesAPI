package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse extends EntityBase {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("addition")
    private AdditionResponse addition;
}
