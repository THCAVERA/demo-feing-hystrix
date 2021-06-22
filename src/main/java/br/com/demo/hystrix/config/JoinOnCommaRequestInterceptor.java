package br.com.demo.hystrix.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class JoinOnCommaRequestInterceptor implements RequestInterceptor {
    public JoinOnCommaRequestInterceptor() {
    }

    public void apply(RequestTemplate template) {
        Iterator var2 = template.queries().entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Collection<String>> entry = (Map.Entry)var2.next();
            if (!((Collection)entry.getValue()).isEmpty()) {
                template.query((String)entry.getKey(), new String[]{String.join(",", (Iterable)entry.getValue())});
            }
        }

    }
}
