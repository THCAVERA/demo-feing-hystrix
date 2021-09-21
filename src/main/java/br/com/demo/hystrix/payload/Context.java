package br.com.demo.hystrix.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Context {
    @JsonProperty("endpoint")
    private String endpoint;
    @JsonProperty("conteudo")
    private Conteudo conteudo;

}
