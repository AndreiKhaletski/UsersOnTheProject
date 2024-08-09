package by.it_academy.service.core;

import jakarta.persistence.Column;
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
public class ProjectDto {
    private UUID uuid;
    @Size(max = 50, message = "Не должно превышать 60 символов")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Должно содержать только латинские буквы")
    private String name;
    @Size(max = 150, message = "Не должно превышать 150 символов")
    @Pattern(regexp = "^^[a-zA-Z ]+$", message = "Должно содержать только латинские буквы")
    private String description;
}
