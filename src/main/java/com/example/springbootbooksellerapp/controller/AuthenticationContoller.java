package com.example.springbootbooksellerapp.controller;


import com.example.springbootbooksellerapp.model.User;
import com.example.springbootbooksellerapp.service.IAuthenticationService;
import com.example.springbootbooksellerapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationContoller {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;



    @PostMapping("sign-up") // api/authentication/sign-up diye client den istek gelir
    public ResponseEntity<?> signUp(@RequestBody User user){

        if (userService.findByUsername(user.getUsername()).isPresent()){

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }


    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user){
        return  new ResponseEntity<>(authenticationService.signInAndReturnJWT(user),HttpStatus.OK);
    }
}
