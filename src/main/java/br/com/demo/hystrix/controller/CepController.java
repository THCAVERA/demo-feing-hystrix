package br.com.demo.hystrix.controller;

import br.com.demo.hystrix.payload.CepPayload;
import br.com.demo.hystrix.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/cep/{cep}")
    public CepPayload getCep(@PathVariable("cep") String cep){
        return cepService.getCep(cep);
    }
}
