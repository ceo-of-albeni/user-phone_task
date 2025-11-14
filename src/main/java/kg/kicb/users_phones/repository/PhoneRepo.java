package kg.kicb.users_phones.repository;

import kg.kicb.users_phones.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepo extends JpaRepository<Phone, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    Phone findByPhoneNumber(String phoneNumber);
}
