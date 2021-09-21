package br.com.demo.hystrix;

import br.com.demo.hystrix.payload.ContextRequest;
import br.com.demo.hystrix.payload.ContextResponse;
import br.com.demo.hystrix.service.ContextoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContextController {

    private final ContextoService service;

    @GetMapping("/simulacao-contexto")
    public List<ContextResponse> getContexto(@RequestBody ContextRequest request){
        return service.getAllContexts(request);
    }
}
