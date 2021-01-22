package main.controllers;

import main.models.User;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user){
        System.out.println(user.getLogin());
        if (userService.save(user)){
            System.out.println("User " + user.getLogin() + " registered successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println("User " + user.getLogin() + " already exists!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/login")
    ResponseEntity<?> logIn(@RequestBody User user){
        System.out.println(user.getLogin() + ", " + user.getPassword());
        if (userService.getMatch(user)){
            System.out.println("User " + user.getLogin() + " entered successfully!");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println("Incorrect login or password!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
