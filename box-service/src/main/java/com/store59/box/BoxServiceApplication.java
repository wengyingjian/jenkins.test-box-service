package com.store59.box;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import com.caucho.hessian.client.HessianProxyFactory;
import com.store59.box.remoting.UserService;

@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = "com.store59")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@PropertySource("classpath:boxservice.properties")
public class BoxServiceApplication extends SpringBootServletInitializer {

    @Value("${userservice.url}")
    private String userServiceUrl;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BoxServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BoxServiceApplication.class, args);
    }

    @Bean
    UserService userService() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        hessianProxyFactory.setOverloadEnabled(true);
        if (!userServiceUrl.endsWith("/")) {
            userServiceUrl = userServiceUrl + "/";
        }
        return (UserService) hessianProxyFactory.create(UserService.class, userServiceUrl + "user");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
