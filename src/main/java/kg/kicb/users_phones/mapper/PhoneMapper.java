package kg.kicb.users_phones.mapper;

import kg.kicb.users_phones.model.Phone;
import kg.kicb.users_phones.model.dto.PhoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PhoneMapper {

    PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

    @Mapping(source = "userId", target = "user.id")
    Phone phoneDtoToPhone (PhoneDto phoneDto);

    @Mapping(source = "user.id", target = "userId")
    PhoneDto phoneToPhoneDto (Phone phone);

    List<PhoneDto> phonesToPhoneDtos (List<Phone> phones);
}
