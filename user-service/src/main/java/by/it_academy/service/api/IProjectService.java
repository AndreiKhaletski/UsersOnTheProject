package by.it_academy.service.api;

import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.service.core.pageofsize.PageOfProjectDto;
import by.it_academy.service.core.ProjectDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProjectService {
    void create(ProjectDto projectDto);

    Optional<ProjectEntity> get(UUID uuid);

    void update(UUID uuid, Long dtUpdate, ProjectDto projectDto);

    void delete(UUID uuid);

    PageOfProjectDto findByMail(Integer page, Integer size);

    Optional<ProjectEntity> findById(UUID uuidProject);

    List<ProjectEntity> allProjects();

}
