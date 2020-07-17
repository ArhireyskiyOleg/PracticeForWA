package org.example;
import org.springframework.http.MediaType;
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
            @PathVariable String token,){
        JDBCPartNumber jdbc =
                new JDBCPartNumber(partnum, pageId - 1, numData);
        return  jdbc;
    }
}
