package md.orange.workouttrackerapi.service.impl;

import lombok.AllArgsConstructor;
import md.orange.workouttrackerapi.dto.UserDTO;
import md.orange.workouttrackerapi.entity.User;
import md.orange.workouttrackerapi.exception.UserException;
import md.orange.workouttrackerapi.repository.UserRepository;
import md.orange.workouttrackerapi.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public void signup(UserDTO input) {
        var user = userRepository.findByUsername(input.getUsername());
        if (user.isPresent()) {
            throw new UserException("Username is already in use");
        }
        User newUser = new User();
        newUser.setUsername(input.getUsername());
        newUser.setPassword(passwordEncoder.encode(input.getPassword()));
        userRepository.save(newUser);
    }

    public User authenticate(UserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
