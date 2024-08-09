package by.it_academy.controller.http;

import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.service.api.IProjectService;
import by.it_academy.service.converter.ConvertProject;
import by.it_academy.service.core.pageofsize.PageOfProjectDto;
import by.it_academy.service.core.ProjectDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private final IProjectService projectService;
    private final ConvertProject convertProject;

    public ProjectController(IProjectService projectService,
                             ConvertProject convertProject) {
        this.projectService = projectService;
        this.convertProject = convertProject;
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProjectDto projectDto){
        projectService.create(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<?> get(@PathVariable ("uuid") UUID uuid){
        Optional<ProjectEntity> entity = projectService.get(uuid);
        if(entity.isPresent()){
            return ResponseEntity.ok(convertProject.convertEntityToDto(entity.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<PageOfProjectDto> get (@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(projectService.findByMail(page, size));
    }

    @PutMapping(value = "/{uuid}/update/{dt_update}")
    public ResponseEntity<?> put(@PathVariable("uuid") UUID uuid,
                                 @PathVariable("dt_update") Long dtUpdate,
                                 @RequestBody ProjectDto projectDto){
        projectService.update(uuid, dtUpdate, projectDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid){
        projectService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
