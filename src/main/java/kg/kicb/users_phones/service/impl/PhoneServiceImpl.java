package kg.kicb.users_phones.service.impl;

import kg.kicb.users_phones.exception.PhoneAlreadyUsedException;
import kg.kicb.users_phones.exception.PhoneNotFoundException;
import kg.kicb.users_phones.exception.UserNotFoundException;
import kg.kicb.users_phones.mapper.PhoneMapper;
import kg.kicb.users_phones.model.Phone;
import kg.kicb.users_phones.model.User;
import kg.kicb.users_phones.model.dto.PhoneDto;
import kg.kicb.users_phones.repository.PhoneRepo;
import kg.kicb.users_phones.repository.UserRepo;
import kg.kicb.users_phones.service.PhoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepo phoneRepo;
    private final UserRepo userRepo;
    private final PhoneMapper phoneMapper = PhoneMapper.INSTANCE;

    public PhoneServiceImpl(PhoneRepo phoneRepo, UserRepo userRepo) {
        this.phoneRepo = phoneRepo;
        this.userRepo = userRepo;
    }

    @Override
    public PhoneDto createPhone(PhoneDto phoneDto) {
        if (phoneRepo.existsByPhoneNumber(phoneDto.getPhoneNumber())) {
            throw new PhoneAlreadyUsedException("Номер телефона используется другим пользователем!");
        }

        User user = userRepo.findById(phoneDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден!"));

        Phone phone = phoneMapper.phoneDtoToPhone(phoneDto);
        phone.setUser(user);
        phone = phoneRepo.save(phone);
        return phoneMapper.phoneToPhoneDto(phone);
    }

    @Override
    public PhoneDto updatePhone(Long id, PhoneDto phoneDto) {
        Phone existingPhone = phoneRepo.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException("Phone с таким ID не найден!"));

        User user = userRepo.findById(phoneDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден!"));

        if (phoneRepo.existsByPhoneNumber(phoneDto.getPhoneNumber())) {
            throw new PhoneAlreadyUsedException("Номер телефона используется другим пользователем!");
        }

        // При попытке задать user_id, к которому номер и так относится, не будет вылетать ошибка PhoneAlreadyUsedException
        Phone phoneWithSameNumber = phoneRepo.findByPhoneNumber(phoneDto.getPhoneNumber());

        if (phoneWithSameNumber != null && !phoneWithSameNumber.getId().equals(id)) {
            throw new PhoneAlreadyUsedException("Номер телефона уже используется другим пользователем!");
        }

        existingPhone.setPhoneNumber(phoneDto.getPhoneNumber());
        existingPhone.setUser(user);

        Phone savedPhone = phoneRepo.save(existingPhone);

        return phoneMapper.phoneToPhoneDto(savedPhone);
    }

    @Override
    public String deletePhone(Long id) {
        Phone existingPhone = phoneRepo.findById(id)
                .orElseThrow(() -> new PhoneNotFoundException("Phone с таким ID не найден!"));

        phoneRepo.delete(existingPhone);


        return "Phone успешно удалён!";
    }

    @Override
    public List<PhoneDto> getAllPhones() {
        List<Phone> phones = phoneRepo.findAll();
        return phoneMapper.phonesToPhoneDtos(phones);
    }
}
