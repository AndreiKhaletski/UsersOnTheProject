package by.it_academy.dao.api;

import by.it_academy.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserResource extends JpaRepository<UserEntity, UUID> {
}
