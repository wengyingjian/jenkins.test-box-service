package com.store59.box;

import com.caucho.hessian.client.HessianProxyFactory;
import com.store59.base.common.api.DormentryApi;
import com.store59.base.common.api.OrderPayAbnormalRecordApi;
import com.store59.base.common.api.RepoApi;
import com.store59.coupon.remoting.CouponService;
import com.store59.dorm.common.api.DormApi;
import com.store59.dorm.common.api.OrderbackRecordApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.net.MalformedURLException;

@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = "com.store59")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@PropertySource("classpath:boxservice.properties")
public class BoxServiceApplication extends SpringBootServletInitializer{

    private static Logger logger = LoggerFactory.getLogger(BoxServiceApplication.class);

    @Value("${baseservice.url}")
    private String repoServiceUrl;
    @Value("${dormservice.url}")
    private String dormServiceUrl;
    @Value("${couponservice.url}")
    private String couponServiceUrl;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BoxServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BoxServiceApplication.class, args);
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
    OrderPayAbnormalRecordApi orderPayAbnormalRecordApi() throws MalformedURLException{
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        hessianProxyFactory.setOverloadEnabled(true);
        if (!repoServiceUrl.endsWith("/")) {
            repoServiceUrl = repoServiceUrl + "/";
        }
        return (OrderPayAbnormalRecordApi) hessianProxyFactory.create(OrderPayAbnormalRecordApi.class, repoServiceUrl + "orderpayabnormalrecord");
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
    OrderbackRecordApi orderbackRecordApi() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        hessianProxyFactory.setOverloadEnabled(true);
        if (!dormServiceUrl.endsWith("/")) {
            dormServiceUrl = dormServiceUrl + "/";
        }
        return (OrderbackRecordApi) hessianProxyFactory.create(OrderbackRecordApi.class, dormServiceUrl + "orderbackrecord");
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
    CouponService couponService() throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        hessianProxyFactory.setOverloadEnabled(true);
        if (!couponServiceUrl.endsWith("/")) {
            couponServiceUrl = couponServiceUrl + "/";
        }
        return (CouponService) hessianProxyFactory.create(CouponService.class, couponServiceUrl + "coupon");
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
