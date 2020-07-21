package com.ovi.user.controller;

import com.ovi.user.exception.UserEmailException;
import com.ovi.user.payload.request.LoginRequest;
import com.ovi.user.payload.request.SignUpRequest;
import com.ovi.user.payload.response.UserDetailsResponseModel;
import com.ovi.user.service.MapValidationErrorService;
import com.ovi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws UserEmailException {
        if (userService.login(loginRequest)) {
            return ResponseEntity.ok("User isValid");
        }
        return new  ResponseEntity<String>( "success" ,null , HttpStatus.OK );
    }

    @PostMapping("/registration")
    public ResponseEntity<?> doRegistration(@Valid @RequestBody SignUpRequest user, BindingResult bindingResult) throws UserEmailException {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(user, bindingResult);
        if (errorMap != null) return errorMap;
        userService.save(user);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @GetMapping(path = "/profile")
    public UserDetailsResponseModel get(@RequestParam String email) throws UserEmailException {
        return userService.showUserInfo(email);
    }
}
