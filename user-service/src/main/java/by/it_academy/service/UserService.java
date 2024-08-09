package by.it_academy.service;

import by.it_academy.dao.api.IUserResource;
import by.it_academy.dao.entity.UserEntity;
import by.it_academy.service.api.IUserService;
import by.it_academy.service.converter.ConvertUser;
import by.it_academy.service.core.pageofsize.PageOfUserDto;
import by.it_academy.service.core.UserDto;
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
public class UserService implements IUserService {

    private final IUserResource userResource;
    private final ConvertUser convertUser;

    public UserService(IUserResource userResource,
                       ConvertUser convertUser) {
        this.userResource = userResource;
        this.convertUser = convertUser;
    }

    @Override
    @Transactional
    public void create(UserDto userDto) {
        UserEntity userEntity = convertUser.convertDtoToEntity(userDto);
        userEntity.setUuid(UUID.randomUUID());
        userResource.saveAndFlush(userEntity);
    }

    @Override
    public Optional<UserEntity> get(UUID uuid) {
        return userResource.findById(uuid);
    }

    @Override
    @Transactional
    public void update(UUID uuid, Long dtUpdate, UserDto userDto) {
        Optional<UserEntity> entity = userResource.findById(uuid);
        if(entity.isPresent()){

            UserEntity userEntity = entity.get();
            userEntity.setName(userDto.getName());
            userEntity.setSurname(userDto.getSurname());
            userEntity.setPatronymic(userDto.getPatronymic());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setJob(userDto.getJob());

            LocalDateTime updateTime = Instant.ofEpochMilli(dtUpdate).atZone(ZoneId.systemDefault()).toLocalDateTime();
            userEntity.setDtUpdate(updateTime);

            userResource.saveAndFlush(userEntity);
        }else{
            throw new IllegalArgumentException("Пользователь не найден");
        }
    }

    @Override
    public PageOfUserDto findByMail(Integer page, Integer size) {
        Page<UserEntity> users = userResource.findAll(PageRequest.of(page, size));
        return getPageUser(users);
    }

    @Override
    @Transactional
    public void delete(UUID uuid) {
        userResource.deleteById(uuid);
    }

    private PageOfUserDto getPageUser(Page<UserEntity> objects) {
        PageOfUserDto pageOfUser = new PageOfUserDto();
        pageOfUser.setNumber(objects.getNumber());
        pageOfUser.setSize(objects.getSize());
        pageOfUser.setTotal_pages(objects.getTotalPages());
        pageOfUser.setNumber_of_elements(objects.getNumberOfElements());
        pageOfUser.setFirst(objects.isFirst());
        pageOfUser.setTotal_elements(objects.getTotalElements());
        pageOfUser.setLast(objects.isLast());

        List<UserDto> userDTOList = objects
                .getContent()
                .stream()
                .map(convertUser::convertEntityToDto)
                .collect(Collectors.toList());

        pageOfUser.setContent(userDTOList);
        return pageOfUser;
    }
}
