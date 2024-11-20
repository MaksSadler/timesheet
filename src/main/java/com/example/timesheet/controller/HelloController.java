package com.example.timesheet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //GET http://localhost:8080/hello?username=Max&city=LosAngeles&date=11/11/2024 - через знак вопроса можно передавать реквест параметры
    //и через амперсанд & можно передавать несколько параметров
    @GetMapping("/hello")
    public String helloPage(@RequestParam(required = false) String username) {
        //String username = "Max";
        return " <h1>Hello " + username + "!</h1> ";
        //return "Hello World!";
    }

    
    @GetMapping("/hello/{username}")
    public String helloPagePathVariable(@PathVariable String username) {
        //String username = "Max";
        return " <h1>Hello " + username + "!</h1> ";
        //return "Hello World!";
    }

}
