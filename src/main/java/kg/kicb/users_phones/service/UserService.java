package kg.kicb.users_phones.service;

import jakarta.validation.Valid;
import kg.kicb.users_phones.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(Long id, @Valid UserDto userDto);

    String deleteUser(Long id);

    List<UserDto> getAllUsers();
}
