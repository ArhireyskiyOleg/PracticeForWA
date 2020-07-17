package org.example.Controllers;
import org.example.Entities.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/registration")

public class RegistrationController {
    @GetMapping(value = "/{username}/{password}/{firstname}/{secondname}/{token}", produces = "application/json")
    public List<User> getData(
            @PathVariable String username,
            @PathVariable String password,
            @PathVariable String firstname,
            @PathVariable String secondname,
            @PathVariable String token){
        return  null;
    }
}
