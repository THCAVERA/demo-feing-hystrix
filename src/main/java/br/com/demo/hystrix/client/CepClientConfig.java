package br.com.demo.hystrix.client;

import br.com.demo.hystrix.config.GenericSetterFactory;
import br.com.demo.hystrix.config.JoinOnCommaRequestInterceptor;
import br.com.demo.hystrix.decoder.CepDecoder;
import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import feign.httpclient.ApacheHttpClient;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

@Slf4j
@Configuration
@ConfigurationProperties("cep.properties")
@Setter
public class CepClientConfig {
    private String url;

    public static HystrixFeign.Builder getDefaultClient(){
        return HystrixFeign.builder()
                .setterFactory(new GenericSetterFactory("CepIntegration"))
                .client(new ApacheHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(15000,15000))
                .errorDecoder(new CepDecoder())
                .logLevel(Logger.Level.FULL)
                .logger(new CepLogger())
                .requestInterceptor(new JoinOnCommaRequestInterceptor());
    }

    @Lazy
    @Bean
    public CepClient cepIntegration(){
        return getDefaultClient()
                .target(CepClient.class,url);
    }

    static class CepLogger extends Logger {
        protected void logRequest(String configKey, Level logLevel, Request request) {
            log.info(" Request Cep --> {}", request.url());
        }
        protected Response logAndRebufferResponse(String configKey, Logger.Level logLevel, Response response, long elapsedTime) throws  IOException {
            String reason = response.reason() != null ? " " + response.reason() : "";
            int status = response.status();
            String strResponse = String.format("Cep Response <--- HTTP/1.1 %s%s (%sms)", status, reason, elapsedTime);
            if (logLevel.ordinal() >= Logger.Level.HEADERS.ordinal()) {
                if (response.body() != null && status != 204 && status != 205) {
                    byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                    if (logLevel.ordinal() >= Logger.Level.FULL.ordinal() && bodyData.length > 0) {
                        log.info(strResponse + " body: {}", new String(bodyData));
                    }
                    return response.toBuilder().body(bodyData).build();
                }
            }
            log.error(strResponse);
            return response;
        }
        protected void log(String configKey, String format, Object... args) {
        }
    }

}
