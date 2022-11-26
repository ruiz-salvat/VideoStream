CREATE TABLE role (
    id bigint NOT NULL,
    role_name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user (
    id bigint NOT NULL,
    user_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    last_name varchar(255),
    address varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE user_roles (
    user_id bigint,
    roles_id bigint,
    CONSTRAINT user_roles_pk PRIMARY KEY (user_id, roles_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT FK_role FOREIGN KEY (roles_id) REFERENCES role(id)
);