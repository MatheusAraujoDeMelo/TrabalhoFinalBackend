package com.example.backend.config;

import com.example.backend.filters.RateLimitFilterBucket4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final RateLimitFilterBucket4j rateLimitFilter;

    public FilterConfig(RateLimitFilterBucket4j rateLimitFilter) {
        this.rateLimitFilter = rateLimitFilter;
    }

    @Bean
    public FilterRegistrationBean<RateLimitFilterBucket4j> rateLimitFilterRegistration() {
        FilterRegistrationBean<RateLimitFilterBucket4j> registration = new FilterRegistrationBean<>();

        // 1. Defina a instância do filtro que você criou
        registration.setFilter(rateLimitFilter);

        // 2. Defina o padrão de URL onde o filtro será aplicado (aqui, em todas as requisições)
        registration.addUrlPatterns("/*");

        // 3. **Importante:** Defina uma ordem de execução.
        // Filtros de limite de taxa geralmente devem ser executados o mais cedo possível.
        registration.setOrder(1);

        return registration;
    }
}