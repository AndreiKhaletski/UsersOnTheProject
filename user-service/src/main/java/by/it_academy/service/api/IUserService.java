package by.it_academy.service.api;

import by.it_academy.dao.entity.UserEntity;
import by.it_academy.service.core.pageofsize.PageOfUserDto;
import by.it_academy.service.core.UserDto;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    void create(UserDto userDto);

    void delete(UUID uuid);

    Optional<UserEntity> get(UUID uuid);

    void update(UUID uuid, Long dtUpdate, UserDto userDto);

    PageOfUserDto findByMail(Integer page, Integer size);
}
