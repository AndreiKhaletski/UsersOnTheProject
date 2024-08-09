package by.it_academy.service.converter;

import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.dao.entity.UserEntity;
import by.it_academy.service.core.ProjectAndUsersDto;
import by.it_academy.service.core.UserToProjectDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ConverterProjectAndUsers {

    public ProjectAndUsersDto convertEntityToDto(ProjectEntity item){
        ProjectAndUsersDto projectDto = new ProjectAndUsersDto();
        projectDto.setName(item.getName());
        projectDto.setDescription(item.getDescription());

        ArrayList<UserToProjectDto> list = new ArrayList<>();
        for (int i = 0; i < item.getUsers().size(); i++) {
            list.add(convertUserEntityToDto(item.getUsers().get(i)));
        }
        projectDto.setUsers(list);
        return projectDto;
    }

    public UserToProjectDto convertUserEntityToDto(UserEntity item){
        UserToProjectDto userToProjectDto = new UserToProjectDto();
        userToProjectDto.setName(item.getName());
        userToProjectDto.setSurname(item.getSurname());
        userToProjectDto.setPatronymic(item.getPatronymic());
        return userToProjectDto;
    }
}
