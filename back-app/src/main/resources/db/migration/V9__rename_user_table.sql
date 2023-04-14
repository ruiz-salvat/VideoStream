ALTER TABLE user RENAME application_user;
ALTER TABLE user_roles RENAME application_user_roles;
ALTER TABLE application_user_roles CHANGE user_id application_user_id bigint;