package by.it_academy.service.converter;

import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.service.core.ProjectDto;
import org.springframework.stereotype.Component;

@Component
public class ConvertProject {

    public ProjectEntity convertDtoToEntity(ProjectDto item){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setUuid(item.getUuid());
        projectEntity.setName(item.getName());
        projectEntity.setDescription(item.getDescription());
        return projectEntity;
    }

    public ProjectDto convertEntityToDto(ProjectEntity item){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setUuid(item.getUuid());
        projectDto.setName(item.getName());
        projectDto.setDescription(item.getDescription());
        return projectDto;
    }
}
