package br.com.demo.hystrix.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContextRequest {
    private String clientId;
    private String canal;
    private BigDecimal valorEmprestimo;
    private Integer quantidadeParcelas;
    private String nomeCupom;
    private String cpf;
}

