package kg.kicb.users_phones.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {

    @NotBlank(message = "Номер обязателен!")
    String phoneNumber;

    @NotNull(message = "User Id обязателен!")
    Long userId;
}
