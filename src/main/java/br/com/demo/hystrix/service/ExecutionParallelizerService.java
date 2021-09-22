package br.com.demo.hystrix.service;


import br.com.demo.hystrix.payload.Context;
import br.com.demo.hystrix.payload.ContextRequest;
import br.com.demo.hystrix.payload.ContextResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
public class ExecutionParallelizerService {

    @Autowired
    private RestTemplate restTemplate;

    @Async
    public CompletableFuture<ContextResponse>  executar(ContextRequest request, Context context){
      return CompletableFuture.supplyAsync(
              () -> this.call(request,context)
      ).handle((res,ex) -> {
          if(ex != null){
              return null;
          }
          return res;
      });
    }



    public ContextResponse call(ContextRequest request, Context context){
        ResponseEntity<ContextResponse> exchange = restTemplate.exchange(context.getEndpoint(), HttpMethod.POST, createEntity(request), ContextResponse.class);

        return exchange.getBody();
    }

    public HttpEntity<ContextRequest> createEntity(ContextRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContextRequest> body =
                new HttpEntity<ContextRequest>(request, headers);
        return body;
    }
}
