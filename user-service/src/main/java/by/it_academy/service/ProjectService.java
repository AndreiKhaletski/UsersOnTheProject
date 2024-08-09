package by.it_academy.service;

import by.it_academy.dao.api.IProjectResource;
import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.service.api.IProjectService;
import by.it_academy.service.converter.ConvertProject;
import by.it_academy.service.core.pageofsize.PageOfProjectDto;
import by.it_academy.service.core.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class ProjectService implements IProjectService {

    private final IProjectResource projectResource;
    private final ConvertProject convertProject;

    public ProjectService(IProjectResource projectResource,
                          ConvertProject convertProject) {
        this.projectResource = projectResource;
        this.convertProject = convertProject;
    }

    @Override
    @Transactional
    public void create(ProjectDto projectDto) {
        ProjectEntity projectEntity = convertProject.convertDtoToEntity(projectDto);
        projectEntity.setUuid(UUID.randomUUID());
        projectResource.saveAndFlush(projectEntity);
    }

    @Override
    public Optional<ProjectEntity> get(UUID uuid) {
        return projectResource.findById(uuid);
    }

    @Override
    @Transactional
    public void update(UUID uuid, Long dtUpdate, ProjectDto projectDto) {
        Optional<ProjectEntity> entity = projectResource.findById(uuid);
        if(entity.isPresent()){

            ProjectEntity projectEntity = entity.get();
            projectEntity.setName(projectDto.getName());
            projectEntity.setDescription(projectDto.getDescription());

            LocalDateTime updateTime = Instant.ofEpochMilli(dtUpdate).atZone(ZoneId.systemDefault()).toLocalDateTime();
            projectEntity.setDtUpdate(updateTime);

            projectResource.saveAndFlush(projectEntity);
        }else{
            throw new IllegalArgumentException("Пользователь не найден");
        }
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        projectResource.deleteById(uuid);
    }

    @Override
    public PageOfProjectDto findByMail(Integer page, Integer size) {
        Page<ProjectEntity> projects = projectResource.findAll(PageRequest.of(page, size));
        return getPageProjects(projects);
    }

    @Override
    public Optional<ProjectEntity> findById(UUID uuidProject) {
        return projectResource.findById(uuidProject);
    }

    @Override
    public List<ProjectEntity> allProjects() {
        return projectResource.findAll();
    }

    private PageOfProjectDto getPageProjects(Page<ProjectEntity> objects) {
        PageOfProjectDto PageOfProjectDto = new PageOfProjectDto();
        PageOfProjectDto.setNumber(objects.getNumber());
        PageOfProjectDto.setSize(objects.getSize());
        PageOfProjectDto.setTotal_pages(objects.getTotalPages());
        PageOfProjectDto.setNumber_of_elements(objects.getNumberOfElements());
        PageOfProjectDto.setFirst(objects.isFirst());
        PageOfProjectDto.setTotal_elements(objects.getTotalElements());
        PageOfProjectDto.setLast(objects.isLast());

        List<ProjectDto> projectsDtoList = objects
                .getContent()
                .stream()
                .map(convertProject::convertEntityToDto)
                .collect(Collectors.toList());

        PageOfProjectDto.setContent(projectsDtoList);
        return PageOfProjectDto;
    }
}
