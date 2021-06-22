package br.com.demo.hystrix.service;

import br.com.demo.hystrix.client.CepClient;
import br.com.demo.hystrix.exception.CepException;
import br.com.demo.hystrix.payload.CepPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    @Autowired
    private CepClient cepClient;

    public CepPayload getCep(String cep){
        CepPayload cepPayload = null;
        try{
           cepPayload = cepClient.getCep(cep);
        }catch(CepException e){
            throw  e;
        }
        return cepPayload;
    }
}
