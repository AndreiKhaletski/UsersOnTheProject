package by.it_academy.dao.entity;

import by.it_academy.service.core.EnumJobTitle;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "users")
public class UserEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "email")
    private String email;
    @Column(name = "job")
    @Enumerated(EnumType.STRING)
    private EnumJobTitle job;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, String surname, String name, String patronymic, String email, EnumJobTitle job, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.job = job;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnumJobTitle getJob() {
        return job;
    }

    public void setJob(EnumJobTitle job) {
        this.job = job;
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
}
