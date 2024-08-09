package by.it_academy.service.core;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private UUID uuid;
    @Size(max = 40, message = "Не должно превышать 40 символов")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Должно содержать только латинские буквы")
    private String surname;
    @Size(max = 20, message = "Не должно превышать 20 символов")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Должно содержать только латинские буквы")
    private String name;
    @Size(max = 40, message = "Не должно превышать 40 символов")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Должно содержать только латинские буквы")
    private String patronymic;
    @Email(message = "Неверный email")
    @Size(max = 50, message = "Пароль не должен превышать 50 символов")
    private String email;
    private EnumJobTitle job;
}
