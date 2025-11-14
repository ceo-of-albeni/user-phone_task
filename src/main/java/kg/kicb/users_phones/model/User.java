package kg.kicb.users_phones.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @GeneratedValue
    @Id
    Long id;
    String name;
    String email;
    LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<Phone> phones;
}
