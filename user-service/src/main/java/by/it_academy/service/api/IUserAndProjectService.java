package by.it_academy.service.api;

import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.service.core.pageofsize.PageOfProjectAndUsersDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserAndProjectService {

    void addUserInProject(UUID uuidUser, UUID uuidProject);

    ProjectEntity getProjectWithUsers(UUID uuidProject);

    List<ProjectEntity> get();
}
