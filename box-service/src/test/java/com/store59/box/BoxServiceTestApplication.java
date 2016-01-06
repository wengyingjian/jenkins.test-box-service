package com.store59.box;

import com.caucho.hessian.client.HessianProxyFactory;
import com.store59.base.common.api.DormentryApi;
import com.store59.base.common.api.RepoApi;
import com.store59.dorm.common.api.DormApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.net.MalformedURLException;

//@EnableCaching
//@PropertySource("classpath:boxservice.properties")
public class BoxServiceTestApplication extends SpringBootServletInitializer {

    @Value("${redis.host:localhost}")
    private String hosts;
    @Value("${redis.port:6379}")
    private int port;
    @Value("${baseservice.url}")
    private String repoServiceUrl;
    @Value("${dormservice.url}")
    private String dormServiceUrl;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BoxServiceTestApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BoxServiceTestApplication.class, args);
    }

    @Bean
    RepoApi repoApi() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        hessianProxyFactory.setOverloadEnabled(true);
        if (!repoServiceUrl.endsWith("/")) {
            repoServiceUrl = repoServiceUrl + "/";
        }
        return (RepoApi) hessianProxyFactory.create(RepoApi.class, repoServiceUrl + "repo");
    }

    @Bean
    DormApi dormApi() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        hessianProxyFactory.setOverloadEnabled(true);
        if (!dormServiceUrl.endsWith("/")) {
            dormServiceUrl = dormServiceUrl + "/";
        }
        return (DormApi) hessianProxyFactory.create(DormApi.class, dormServiceUrl + "dorm");
    }

    @Bean
    DormentryApi dormentryApi() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        hessianProxyFactory.setOverloadEnabled(true);
        if (!dormServiceUrl.endsWith("/")) {
            dormServiceUrl = dormServiceUrl + "/";
        }
        return (DormentryApi) hessianProxyFactory.create(DormentryApi.class, dormServiceUrl + "dormentry");
    }

    @Bean
    JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setHostName(hosts);
        jedisConnectionFactory.setPort(port);
        return jedisConnectionFactory;
    }

    @Bean
    RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory());
        return redisTemplate;
    }

    @Bean
    CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        redisCacheManager.setUsePrefix(true);
        redisCacheManager.setDefaultExpiration(30);
        return redisCacheManager;
    }

}
