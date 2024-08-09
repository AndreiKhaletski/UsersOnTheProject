package by.it_academy.dao.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema = "app", name = "projects_users")
public class UserAndProjectEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "uuid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "id_project", referencedColumnName = "uuid")
    private ProjectEntity projectEntity;


    public UserAndProjectEntity() {
    }

    public UserAndProjectEntity(UUID uuid,
                                UserEntity userEntity,
                                ProjectEntity projectEntity) {
        this.uuid = uuid;
        this.userEntity = userEntity;
        this.projectEntity = projectEntity;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }
}
