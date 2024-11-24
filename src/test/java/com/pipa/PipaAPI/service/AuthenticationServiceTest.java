package com.pipa.PipaAPI.service;

import com.pipa.PipaAPI.domain.entity.Family;
import com.pipa.PipaAPI.domain.entity.Professional;
import com.pipa.PipaAPI.domain.entity.User;
import com.pipa.PipaAPI.domain.enums.UserType;
import com.pipa.PipaAPI.domain.models.auth.AuthenticationRequest;
import com.pipa.PipaAPI.domain.models.auth.AuthenticationResponse;
import com.pipa.PipaAPI.domain.models.auth.RegisterRequest;
import com.pipa.PipaAPI.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {}

    @Test
    public void registerTest() {
        RegisterRequest registerRequest = new RegisterRequest("username", "password", UserType.ADMIN, new Family(), new Professional());
        User user = User.builder()
                .username("username")
                .password("encodedPassword")
                .typeUser(UserType.ADMIN)
                .family(new Family())
                .professional(new Professional())
                .build();
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(jwtService.generateToken(user)).thenReturn("token");

        AuthenticationResponse response = authenticationService.register(registerRequest);

        assertEquals("token", response.getToken());
    }

    @Test
    public void authenticateTest() {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("username", "password");
        User user = User.builder().username("username").password("encodedPassword").build();
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("token");

        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        assertEquals("token", response.getToken());
    }

    @Test
    public void findByUsername() {
        String username = "teste";

        when(this.userRepository.findByUsername(username)).thenReturn(Optional.of(new User()));

        User result = this.authenticationService.findByUsername(username);

        assertNotNull(result);
    }

    @Test
    public void findByUsernameNotFoundExceptionTest() {
        try {
            User result = this.authenticationService.findByUsername(null);
            assertNull(result);
        } catch (ResponseStatusException e) {
            assertEquals("404 NOT_FOUND \"The user with username 'null' not found\"", e.getMessage());
            assertThrows(ResponseStatusException.class, () -> this.authenticationService.findByUsername(null));
        }
    }
}