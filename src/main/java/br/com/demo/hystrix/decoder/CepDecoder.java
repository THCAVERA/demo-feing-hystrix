package br.com.demo.hystrix.decoder;

import br.com.demo.hystrix.exception.CepException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CepDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return this.translate(response);
    }

    public Exception translate(Response response){
        switch(response.status()){
            default:
            case 400:
                return new CepException();
        }
    }
}
