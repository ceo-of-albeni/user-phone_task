package kg.kicb.users_phones.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.kicb.users_phones.model.dto.UserDto;
import kg.kicb.users_phones.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller", description = "User controller")
public class UserController {

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    @Operation(summary = "Создание пользователя.", description = "В этом методе создается объект User.")
    @ApiResponse(responseCode = "201", description = "User успешно создан!")
    @ApiResponse(responseCode = "400", description = "User с такой почтой уже существует!")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userDto = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление данных пользователя.", description = "В этом методе обновляются данные объекта User.")
    @ApiResponse(responseCode = "200", description = "User успешно обновлен!")
    @ApiResponse(responseCode = "400", description = "User с такой почтой уже существует!")
    @ApiResponse(responseCode = "404", description = "User не найден!")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        userDto = userService.updateUser(id, userDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя", description = "В этом методе удаляется объект User.")
    @ApiResponse(responseCode = "200", description = "User успешно удален!")
    @ApiResponse(responseCode = "404", description = "User не найден!")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        String message = userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @GetMapping("")
    @Operation(summary = "Просмотр всех пользователей", description = "В этом методе можно получить список всех пользователей.")
    @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен!")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
