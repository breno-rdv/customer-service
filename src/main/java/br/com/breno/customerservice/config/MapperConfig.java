package br.com.breno.customerservice.config;

import br.com.breno.customerservice.dto.CustomerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CustomerMapper customerMapper() {
        return CustomerMapper.INSTANCE;
    }

}
