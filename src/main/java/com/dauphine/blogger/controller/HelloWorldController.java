package com.dauphine.blogger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("hello")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("hello/{name}")
    public String helloPath(@RequestParam String name) {
        return "Hello " + name;
    }
}
