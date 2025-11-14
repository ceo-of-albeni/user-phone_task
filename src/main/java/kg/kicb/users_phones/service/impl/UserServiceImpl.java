package kg.kicb.users_phones.service.impl;

import kg.kicb.users_phones.exception.EmailAlreadyUsedException;
import kg.kicb.users_phones.exception.UserNotFoundException;
import kg.kicb.users_phones.mapper.UserMapper;
import kg.kicb.users_phones.model.User;
import kg.kicb.users_phones.model.dto.UserDto;
import kg.kicb.users_phones.repository.UserRepo;
import kg.kicb.users_phones.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyUsedException("Email уже используется другим пользователем!");
        }

        User user = userMapper.userDtoToUser(userDto);
        user = userRepo.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User с таким ID не найден!"));

        if (userRepo.existsByEmailAndIdNot(userDto.getEmail(), id)) {
            throw new EmailAlreadyUsedException("Email уже используется другим пользователем!");
        }

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setDateOfBirth(userDto.getDateOfBirth());

        User savedUser = userRepo.save(existingUser);

        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public String deleteUser(Long id) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User с таким ID не найден!"));

        userRepo.delete(existingUser);

        return "User успешно удалён!";
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return userMapper.usersToUserDtos(users);
    }
}
