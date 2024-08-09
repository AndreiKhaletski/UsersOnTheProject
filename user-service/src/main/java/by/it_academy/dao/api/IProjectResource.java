package by.it_academy.dao.api;

import by.it_academy.dao.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProjectResource extends JpaRepository<ProjectEntity, UUID> {
}
