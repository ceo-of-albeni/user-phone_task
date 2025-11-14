package kg.kicb.users_phones.mapper;

import kg.kicb.users_phones.model.User;
import kg.kicb.users_phones.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser (UserDto userDto);
    UserDto userToUserDto (User user);

    List<UserDto> usersToUserDtos (List<User> users);
}
