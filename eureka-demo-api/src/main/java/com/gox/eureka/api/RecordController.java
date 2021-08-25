package com.gox.eureka.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController {

    @RequestMapping("/records")
    public List<String> records(){
        return List.of("Hello", "there", "whats", "up");
    }
}
