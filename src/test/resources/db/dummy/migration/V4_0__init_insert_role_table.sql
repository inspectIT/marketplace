CREATE TABLE role_entity (
	role_uuid     UUID PRIMARY KEY, --     DEFAULT random_uuid(),
	role          VARCHAR2(255) NOT NULL,

	creation_date DATETIME      NOT NULL DEFAULT now(),
	version       INTEGER       NOT NULL DEFAULT 1
);

ALTER TABLE user_entity
	ADD role_uuid UUID;
ALTER TABLE user_entity
	ADD CONSTRAINT fk_role_uuid FOREIGN KEY (role_uuid) REFERENCES role_entity (role_uuid);

ALTER TABLE user_entity
	ADD avatar_url VARCHAR2(255);
ALTER TABLE user_entity
	ADD company VARCHAR2(255);
ALTER TABLE user_entity
	ADD location VARCHAR2(255);

/**
 * insert
 */
INSERT INTO role_entity (role_uuid, role) VALUES ('725727b967bf4ef195d815ad3ee8d159', 'user');
INSERT INTO role_entity (role_uuid, role) VALUES ('a2e1353ccd6944a783205cf29fcb160a', 'admin');

/**
 * update user
 */
UPDATE user_entity
SET role_uuid = 'a2e1353ccd6944a783205cf29fcb160a'
WHERE user_uuid = 'c848cad7c3b2499a96888fb2a261313b';
UPDATE user_entity
SET role_uuid = '725727b967bf4ef195d815ad3ee8d159'
WHERE user_uuid = '23c61362911e4438876507b019949570';
UPDATE user_entity
SET role_uuid = '725727b967bf4ef195d815ad3ee8d159'
WHERE user_uuid = '6dd1c1473c53404386932f106b368a2e';