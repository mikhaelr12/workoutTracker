package md.orange.workouttrackerapi.controller;

import lombok.AllArgsConstructor;
import md.orange.workouttrackerapi.config.JwtService;
import md.orange.workouttrackerapi.dto.UserDTO;
import md.orange.workouttrackerapi.dto.response.LoginResponse;
import md.orange.workouttrackerapi.entity.User;
import md.orange.workouttrackerapi.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO input) {
        authenticationService.signup(input);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserDTO input) {
        User authenticatedUser = authenticationService.authenticate(input);
        String token = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
