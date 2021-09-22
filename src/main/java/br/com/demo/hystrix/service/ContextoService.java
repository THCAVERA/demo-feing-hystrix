package br.com.demo.hystrix.service;

import br.com.demo.hystrix.payload.Context;
import br.com.demo.hystrix.payload.ContextRequest;
import br.com.demo.hystrix.payload.ContextResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContextoService {

    private final ReadFromJsonService readFromJsonService;
    private final ExecutionParallelizerService executionParallelizerService;


    public List<ContextResponse> getAllContexts(ContextRequest contextRequest){
        List<Context> contexts = readFromJsonService.readFromFile();
        List<CompletableFuture<ContextResponse>> listExecs = new ArrayList<>();
        contexts.forEach( c ->{
             listExecs.add(executionParallelizerService.executar(contextRequest, c));
        });

        listExecs.forEach(e -> CompletableFuture.allOf(e).join());

        return listExecs.stream().map(future -> this.getListContextResponse(future)).collect(Collectors.toList());

    }

    public ContextResponse getListContextResponse(CompletableFuture<ContextResponse> future){
        ContextResponse contextResponse = new ContextResponse();
        try{
            contextResponse = future.get();
        }catch (InterruptedException | ExecutionException e){

        }
        return contextResponse;
    }


}
