package com.rumwei.springcloud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @GetMapping("/testa")
    public String testa(){
        return "-----testA";
    }
    @GetMapping("/testb")
    public String testb(){
        return "-----testB";
    }
}
