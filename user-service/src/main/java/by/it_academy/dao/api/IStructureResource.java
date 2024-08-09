package by.it_academy.dao.api;

import by.it_academy.dao.entity.UserAndProjectEntity;
import by.it_academy.dao.entity.ProjectEntity;
import by.it_academy.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IStructureResource extends JpaRepository<UserAndProjectEntity, UUID> {
    List<UserAndProjectEntity> findByProjectEntityUuid(UUID projectId);

    boolean existsByUserEntityAndProjectEntity(UserEntity userEntity, ProjectEntity projectEntity);
}
