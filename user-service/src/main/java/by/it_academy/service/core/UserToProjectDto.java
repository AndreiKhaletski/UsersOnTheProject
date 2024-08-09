package by.it_academy.service.core;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserToProjectDto {
    private String surname;
    private String name;
    private String patronymic;
}
