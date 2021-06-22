package br.com.demo.hystrix.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import feign.Feign;
import feign.Target;
import feign.hystrix.SetterFactory;

import java.lang.reflect.Method;

public class GenericSetterFactory implements SetterFactory {
    private String group;

    public GenericSetterFactory(String group) {
        this.group = group;
    }

    public HystrixCommand.Setter create(Target<?> target, Method method) {
        String groupKey = group;
        String commandKey = Feign.configKey(target.type(), method);
        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                .andCommandKey(com.netflix.hystrix.HystrixCommandKey.Factory.asKey(commandKey));
    }
}
