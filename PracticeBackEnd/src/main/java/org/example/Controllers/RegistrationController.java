package org.example.Controllers;
import org.example.Entities.User;
import org.example.Services.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/registration")

public class RegistrationController {
    @GetMapping(value = "/{username}/{password}/{firstname}/{secondname}", produces = "application/json")
    public void getData(
            @PathVariable String username,
            @PathVariable String password,
            @PathVariable String firstname,
            @PathVariable String secondname){

        RegistrationService reg = new RegistrationService(username, password, firstname, secondname);
        reg.RegistrationUser();
    }
}
