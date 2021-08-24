package com.gox.eureka.client.demo.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    public TestController(@Qualifier("eurekaClient") EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @RequestMapping("/")
    public String greeting() {
        StringBuilder sb = new StringBuilder();
        eurekaClient
                .getApplication(appName)
                .getInstances()
                .stream()
                .forEach(i -> sb.append(i.getHostName()).append(":").append(i.getPort()));
        return sb.toString();
    }
}
