package com.gox.eureka.client.demo.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @RequestMapping("/payloads")
    public ResponseEntity<String> records(){

        List<InstanceInfo> instList = eurekaClient
                .getApplication("spring-cloud-eureka-api")
                .getInstances();

        if(instList == null || instList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        InstanceInfo inst = instList.get(0);
        String recordsServiceEndpoint = "http://" + inst.getHostName() + ":" + inst.getPort() + "/records";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> response = restTemplate.getForEntity(recordsServiceEndpoint, List.class);
        return new ResponseEntity<>(String.join(" ", response.getBody()), HttpStatus.OK);
    }
}
