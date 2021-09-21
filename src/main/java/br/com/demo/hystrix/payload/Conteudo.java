package br.com.demo.hystrix.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Conteudo {
    @JsonProperty("habilitado")
    private boolean habilitado;
    @JsonProperty("duracao")
    private String duracao;
    @JsonProperty("invalidaQuando")
    private InvalidaQuando invalidaQuando;
}
