package md.orange.workouttrackerapi.service;

import md.orange.workouttrackerapi.dto.UserDTO;
import md.orange.workouttrackerapi.entity.User;

public interface AuthenticationService {
    void signup(UserDTO input);

    User authenticate(UserDTO input);
}
