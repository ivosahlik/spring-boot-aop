
package cz.ivosahlik.controller;

import cz.ivosahlik.model.domain.User;
import cz.ivosahlik.model.dto.ResultDTO;
import cz.ivosahlik.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "This is home Controller!";
    }

    @ResponseBody
    @PostMapping("/api/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultDTO addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}
