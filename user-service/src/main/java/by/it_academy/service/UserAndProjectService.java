package by.it_academy.service;

import by.it_academy.dao.api.IStructureResource;
import by.it_academy.dao.entity.UserAndProjectEntity;
import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.dao.entity.UserEntity;
import by.it_academy.service.api.IUserAndProjectService;
import by.it_academy.service.api.IProjectService;
import by.it_academy.service.api.IUserService;
import by.it_academy.service.converter.ConvertProject;
import by.it_academy.service.converter.ConverterProjectAndUsers;
import by.it_academy.service.core.ProjectDto;
import by.it_academy.service.core.UserToProjectDto;
import by.it_academy.service.core.pageofsize.PageOfProjectAndUsersDto;
import by.it_academy.service.core.pageofsize.PageOfProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserAndProjectService implements IUserAndProjectService {

    private final IProjectService projectService;
    private final IUserService userService;
    private final IStructureResource structureResource;
    private final ConverterProjectAndUsers converter;
    private final ConvertProject convertProject;

    public UserAndProjectService(IProjectService projectService,
                                 IUserService userService,
                                 IStructureResource structureResource,
                                 ConverterProjectAndUsers converter,
                                 ConvertProject convertProject) {
        this.projectService = projectService;
        this.userService = userService;
        this.structureResource = structureResource;
        this.converter = converter;
        this.convertProject = convertProject;
    }

    @Transactional
    @Override
    public void addUserInProject(UUID uuidUser, UUID uuidProject) {

        Optional<UserEntity> userEntity = userService.get(uuidUser);
        if(userEntity.isEmpty()){
            throw new IllegalArgumentException("Такого пользователя не существует!");
        }

        Optional<ProjectEntity> projectEntity = projectService.get(uuidProject);
        if (projectEntity.isEmpty()){
            throw new IllegalArgumentException("Такого проекта не существует!");
        }

        UserAndProjectEntity userAndProjectEntity = new UserAndProjectEntity();
        userAndProjectEntity.setUuid(UUID.randomUUID());
        userAndProjectEntity.setUserEntity(userEntity.get());
        userAndProjectEntity.setProjectEntity(projectEntity.get());
        structureResource.saveAndFlush(userAndProjectEntity);
    }

    @Override
    public ProjectEntity getProjectWithUsers(UUID uuidProject) {
        Optional<ProjectEntity> projectEntity = projectService.findById(uuidProject);

        if(projectEntity.isPresent()){
            List<UserAndProjectEntity> userAndProjectEntities = structureResource.findByProjectEntityUuid(uuidProject);

            List<UserEntity> users = new ArrayList<>();
            for (UserAndProjectEntity employeeAndProject : userAndProjectEntities) {
                UserEntity user = employeeAndProject.getUserEntity();
                users.add(user);
            }
            projectEntity.get().setUsers(users);
        }else{
            throw new IllegalArgumentException("Такого проекта не существует!");
        }
        return projectEntity.get();
    }

    @Override
    public List<ProjectEntity> get() {
        List<ProjectEntity> projects = projectService.allProjects();

        for (ProjectEntity project : projects) {
            List<UserAndProjectEntity> userAndProjectEntities =
                    structureResource.findByProjectEntityUuid(project.getUuid());

            List<UserEntity> users = new ArrayList<>();
            for (UserAndProjectEntity userAndProject : userAndProjectEntities) {
                UserEntity user = userAndProject.getUserEntity();
                users.add(user);
            }
            project.setUsers(users);
        }
        return projects;
    }
}
