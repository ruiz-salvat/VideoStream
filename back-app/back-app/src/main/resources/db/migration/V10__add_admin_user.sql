INSERT
INTO
  application_user
  (id, user_name, email, password, name, last_name, address)
VALUES
  (1, 'admin', '', '$2a$10$9zvHKeXG3zhc8d9hnm1TEOrcW29reM2eoLVQ2Nc/s75tLY3OTgQCu', 'admin', NULL, '');

INSERT
INTO
  application_user_roles
  (application_user_id, roles_id)
VALUES
  (1, 1);