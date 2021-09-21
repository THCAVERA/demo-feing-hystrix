package br.com.demo.hystrix.service;

import br.com.demo.hystrix.payload.Context;
import br.com.demo.hystrix.payload.ContextRequest;
import br.com.demo.hystrix.payload.ContextResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContextoService {

    private final ReadFromJsonService readFromJsonService;
    private final RestTemplate restTemplate;

    public List<ContextResponse> getAllContexts(ContextRequest contextRequest){
        List<Context> contexts = readFromJsonService.readFromFile();
       return  Arrays.asList(new ContextResponse());
    }

}
