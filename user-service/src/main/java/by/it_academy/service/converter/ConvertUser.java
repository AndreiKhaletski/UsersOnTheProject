package by.it_academy.service.converter;

import by.it_academy.dao.entity.UserEntity;
import by.it_academy.service.core.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ConvertUser {

    public UserEntity convertDtoToEntity (UserDto item){
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(item.getUuid());
        userEntity.setSurname(item.getSurname());
        userEntity.setName(item.getName());
        userEntity.setPatronymic(item.getPatronymic());
        userEntity.setEmail(item.getEmail());
        userEntity.setJob(item.getJob());
        return userEntity;
    }

    public UserDto convertEntityToDto (UserEntity item){
        UserDto userDto = new UserDto();
        userDto.setUuid(item.getUuid());
        userDto.setSurname(item.getSurname());
        userDto.setName(item.getName());
        userDto.setPatronymic(item.getPatronymic());
        userDto.setEmail(item.getEmail());
        userDto.setJob(item.getJob());
        return userDto;
    }
}
