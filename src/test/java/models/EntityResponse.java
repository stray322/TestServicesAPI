package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EntityResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("verified")
    private boolean verified;

    @JsonProperty("importantNumbers")
    private List<Integer> importantNumbers;

    @JsonProperty("addition")
    private Addition addition;

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Addition {
        @JsonProperty("additionalInfo")
        private String additionalInfo;

        @JsonProperty("additionalNumber")
        private int additionalNumber;

        @JsonProperty("id")
        private Integer id;
    }
}
