package br.com.demo.hystrix.client;

import br.com.demo.hystrix.payload.CepPayload;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "cep-client")
public interface CepClient {
    @RequestLine("GET /ws/{cep}/json/")
    @Headers(value = {
            "Content-Type: application/json; charset=utf-8",
            "Accept: application/jsom;charset=utf-8"
    })
    CepPayload getCep(@Param("cep") String cep);
}
