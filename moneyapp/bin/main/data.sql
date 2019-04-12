INSERT INTO users (username, password, enabled)
	VALUES ('admin', '$2a$10$aafN/Q3bdm5oJe4F1goRt.Ba6rBPFjDOseErNq7psynf9znRJJ2Ty', 1);

INSERT INTO users (username, password, enabled)
	VALUES ('user', '$2a$10$HR8oBjGoo4IxqfnmOWJVeuHXKa9Uuf6IcSoBSqkTLs1PA8IwE6B5W', 1);

INSERT INTO authorities (username, authority)
	VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
	VALUES ('admin', 'ROLE_USER');

INSERT INTO authorities (username, authority)
	VALUES ('user', 'ROLE_USER');