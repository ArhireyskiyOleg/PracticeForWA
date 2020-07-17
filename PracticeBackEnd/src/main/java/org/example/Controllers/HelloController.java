package org.example.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE )
    public String sayHello(){
        return "hello";
    }
}
