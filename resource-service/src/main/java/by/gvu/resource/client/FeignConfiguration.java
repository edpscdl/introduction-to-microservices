package by.gvu.resource.client;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;


public class FeignConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Content-Type", "application/json");
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
