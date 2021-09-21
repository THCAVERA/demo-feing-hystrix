package br.com.demo.hystrix.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContextResponse {
    private String nome;
    private boolean elegivel;
    private boolean aplicado;
    private Map<String,String> dadosExtra;
    }
