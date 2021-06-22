package br.com.demo.hystrix.handler;

import br.com.demo.hystrix.exception.CepException;
import br.com.demo.hystrix.payload.CepHandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CepHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CepException.class)
    public CepHandlerResponse handlerBadRequest(CepException e){
        return CepHandlerResponse.builder()
                .mensagem("CEP INVÁLIDO")
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
