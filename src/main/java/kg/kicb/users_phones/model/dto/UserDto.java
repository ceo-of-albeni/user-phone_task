package kg.kicb.users_phones.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Имя обязательно!")
    String name;

    @NotBlank(message = "Почта обязательна!")
    String email;

    @NotNull(message = "Дата рождения обязательна!")
    LocalDate dateOfBirth;
}
