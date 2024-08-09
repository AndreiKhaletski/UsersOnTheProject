package by.it_academy.controller.http;

import by.it_academy.dao.entity.UserEntity;
import by.it_academy.service.api.IUserService;
import by.it_academy.service.converter.ConvertUser;
import by.it_academy.service.core.pageofsize.PageOfUserDto;
import by.it_academy.service.core.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final IUserService userService;
    private final ConvertUser convertUser;

    public UserController(IUserService userService,
                          ConvertUser convertUser) {
        this.userService = userService;
        this.convertUser = convertUser;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto){
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<?> get(@PathVariable ("uuid") UUID uuid){
        Optional<UserEntity> entity = userService.get(uuid);
        if (entity.isPresent()){
            return ResponseEntity.ok(convertUser.convertEntityToDto(entity.get()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<PageOfUserDto> get (@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(userService.findByMail(page, size));
    }

    @PutMapping(value = "/{uuid}/update/{dt_update}")
    public ResponseEntity<?> put(@PathVariable("uuid") UUID uuid,
                                 @PathVariable("dt_update") Long dtUpdate,
                                 @RequestBody UserDto userDto){
        userService.update(uuid, dtUpdate, userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid){
        userService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
