package kg.kicb.users_phones.service;

import jakarta.validation.Valid;
import kg.kicb.users_phones.model.dto.PhoneDto;

import java.util.List;

public interface PhoneService {
    PhoneDto createPhone(PhoneDto phoneDto);

    PhoneDto updatePhone(Long id, @Valid PhoneDto phoneDto);

    String deletePhone(Long id);

    List<PhoneDto> getAllPhones();
}
