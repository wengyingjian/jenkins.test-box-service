package com.store59.box.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhangwangyong on 15/8/11.
 */
@Component
public class RestClient {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${sms.server.root.url}")
    private String SMS_ROOT_URL;

    public void sendMessageWithPhone(String phone, String content) {
        restTemplate.getForObject(SMS_ROOT_URL + "/smsPhone?phone={phone}&content={content}", String.class, phone, content);
    }
}
