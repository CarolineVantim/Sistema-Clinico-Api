package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.User;
import com.pipa.PipaAPI.domain.enums.UserType;
import com.pipa.PipaAPI.domain.models.auth.AuthenticationRequest;
import com.pipa.PipaAPI.domain.models.auth.AuthenticationResponse;
import com.pipa.PipaAPI.domain.models.auth.RegisterRequest;
import com.pipa.PipaAPI.domain.repository.UserRepository;
import com.pipa.PipaAPI.rest.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository repository, UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager){
        this.repository = repository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .typeUser(request.getUserType())
                .professional(request.getProfessional())
                .family(request.getFamily())
                .build();
        this.userService.save(UserDTO.toDTO(user));
        var jwtToken = jwtService.generateToken(user);
        UserType userType = this.userService.getUserType(request.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userType(userType)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "This user has no authorization the access", e);
        }
        var user = this.repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with username '%s' not found".formatted(request.getUsername())));

        var jwtToken = jwtService.generateToken(user);
        UserType userType = this.userService.getUserType(request.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userType(userType)
                .build();
    }

    public User findByUsername(String username) {
        return this.repository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The user with username '%s' not found".formatted(username)));
    }
}
