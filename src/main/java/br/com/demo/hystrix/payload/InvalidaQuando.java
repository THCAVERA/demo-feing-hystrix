package br.com.demo.hystrix.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidaQuando {
    @JsonProperty("campo")
    private String campo;
    @JsonProperty("operator")
    private String operator;
    @JsonProperty("valor")
    private String valor;
}
