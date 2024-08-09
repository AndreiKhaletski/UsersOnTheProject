CREATE ROLE "works_app" WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT -1
	PASSWORD '855312';

CREATE DATABASE employee_structure
    WITH
    OWNER = "works_app"
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA app
    AUTHORIZATION "works_app";

CREATE TABLE app.users(
    uuid UUID NOT NULL PRIMARY KEY,
    surname VARCHAR(255),
    name VARCHAR(255),
    patronymic VARCHAR(255),
    email VARCHAR(255),
    job VARCHAR(255),
    dt_create timestamp WITHOUT TIME ZONE NOT NULL,
    dt_update timestamp WITHOUT TIME ZONE NOT NULL
);

ALTER TABLE IF EXISTS app.users
    OWNER to "works_app";

CREATE TABLE app.projects(
    uuid UUID NOT NULL PRIMARY KEY,
    name_projects VARCHAR(255),
    description VARCHAR(255),
    dt_create timestamp WITHOUT TIME ZONE NOT NULL,
    dt_update timestamp WITHOUT TIME ZONE NOT NULL
);

ALTER TABLE IF EXISTS app.projects
    OWNER to "works_app";

CREATE TABLE app.projects_users(
    uuid UUID NOT NULL PRIMARY KEY,
    id_user UUID NOT NULL,
    id_project UUID NOT NULL
);

ALTER TABLE IF EXISTS app.projects_users
    OWNER to "works_app";

ALTER TABLE app.projects_users
ADD CONSTRAINT fk_projects_users_user FOREIGN KEY (id_user) REFERENCES app.users (uuid);

ALTER TABLE app.projects_users
ADD CONSTRAINT fk_projects_users_project FOREIGN KEY (id_project) REFERENCES app.projects (uuid);