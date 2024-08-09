package by.it_academy.controller.http;

import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.service.api.IUserAndProjectService;
import by.it_academy.service.converter.ConverterProjectAndUsers;
import by.it_academy.service.core.ProjectAndUsersDto;
import by.it_academy.service.core.pageofsize.PageOfProjectAndUsersDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/work")
public class EmployeeAndProjectController {

    private final IUserAndProjectService userAndProjectService;
    private final ConverterProjectAndUsers converter;

    public EmployeeAndProjectController(IUserAndProjectService userAndProjectService,
                                        ConverterProjectAndUsers converter) {
        this.userAndProjectService = userAndProjectService;
        this.converter = converter;
    }


    @PostMapping(value = "/{uuid_project}/add-project/{uuid_user}")
    public ResponseEntity<?> add(@PathVariable ("uuid_project") UUID uuid_project,
                                 @PathVariable ("uuid_user") UUID uuid_user){
        userAndProjectService.addUserInProject(uuid_user, uuid_project);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{uuid_project}")
    public ResponseEntity<?> getProject (@PathVariable ("uuid_project") UUID uuid_project){
        ProjectAndUsersDto projectAndUsersDto =
                converter.convertEntityToDto(userAndProjectService.getProjectWithUsers(uuid_project));
        return ResponseEntity.ok(projectAndUsersDto);
    }

    @GetMapping(value = "/all-projects")
    public ResponseEntity<?> allProjects(){
        ArrayList<ProjectAndUsersDto> listDto = new ArrayList<>();
        for (int i = 0; i < userAndProjectService.get().size(); i++) {
            listDto.add(converter.convertEntityToDto(userAndProjectService.get().get(i)));
        }
        return ResponseEntity.ok(listDto);
    }
}
