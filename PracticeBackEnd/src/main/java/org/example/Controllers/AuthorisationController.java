package org.example.Controllers;

import org.example.Services.AuthorisationServicve;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")

public class AuthorisationController {
    @GetMapping(value = "/{username}/{password}", produces = "application/json")
    public String getToken( @PathVariable String username, @PathVariable String password){
        AuthorisationServicve auth = new AuthorisationServicve(username, password);
        return auth.getToken();
    }
}
