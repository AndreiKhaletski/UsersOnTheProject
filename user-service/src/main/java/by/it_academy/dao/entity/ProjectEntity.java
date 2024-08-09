package by.it_academy.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "projects")
public class ProjectEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "name_projects")
    private String name;
    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "projects_users", schema = "app",
            joinColumns = @JoinColumn(name = "id_project", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "uuid"))
    private List<UserEntity> users;

    public ProjectEntity() {
    }

    public ProjectEntity(UUID uuid, String name, String description, LocalDateTime dtCreate, LocalDateTime dtUpdate, List<UserEntity> users) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.users = users;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
