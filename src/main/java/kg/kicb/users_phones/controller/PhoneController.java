package kg.kicb.users_phones.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.kicb.users_phones.model.dto.PhoneDto;
import kg.kicb.users_phones.service.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/phone")
@Tag(name = "Phone Controller", description = "Phone controller")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping("")
    @Operation(summary = "Создание номера.", description = "В этом методе создается объект Phone.")
    @ApiResponse(responseCode = "201", description = "Phone успешно создан!")
    public ResponseEntity<?> createPhone(@Valid @RequestBody PhoneDto phoneDto) {
        phoneDto = phoneService.createPhone(phoneDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление данных номера.", description = "В этом методе обновляются данные объекта Phone.")
    @ApiResponse(responseCode = "200", description = "Данные Phone успешно обновлены!")
    @ApiResponse(responseCode = "404", description = "Phone не найден!")
    public ResponseEntity<?> updatePhone(@PathVariable Long id, @RequestBody @Valid PhoneDto phoneDto) {
        phoneDto = phoneService.updatePhone(id, phoneDto);
        return ResponseEntity.ok(phoneDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление номера.", description = "В этом методе удаляется объект Phone.")
    @ApiResponse(responseCode = "200", description = "Phone успешно удален!")
    @ApiResponse(responseCode = "404", description = "Phone не найден!")
    public ResponseEntity<?> deletePhone(@PathVariable Long id) {
        String message = phoneService.deletePhone(id);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @GetMapping("")
    @Operation(summary = "Просмотр всех номеров.", description = "В этом методе можно получить список всех номеров.")
    @ApiResponse(responseCode = "200", description = "Список номеров успешно получен!")
    public ResponseEntity<List<PhoneDto>> getAllPhones() {
        List<PhoneDto> phones = phoneService.getAllPhones();
        return ResponseEntity.ok(phones);
    }

}
