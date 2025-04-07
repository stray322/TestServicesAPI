package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EntityBase {
    @JsonProperty("title")
    private String title;

    @JsonProperty("verified")
    private boolean verified;

    @JsonProperty("importantNumbers")
    private List<Integer> importantNumbers;
}
