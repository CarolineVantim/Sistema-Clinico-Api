package com.pipa.PipaAPI.rest.controller;

import com.pipa.PipaAPI.domain.models.auth.AuthenticationRequest;
import com.pipa.PipaAPI.domain.models.auth.AuthenticationResponse;
import com.pipa.PipaAPI.domain.models.auth.RegisterRequest;
import com.pipa.PipaAPI.service.AuthenticationService;
import com.pipa.PipaAPI.utils.swagger.AuthenticationSwaggerOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController implements AuthenticationSwaggerOperations {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){;
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
