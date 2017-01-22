/**
 * flyway example;
 * dummy db for simple read/write operation
 *
 * keep me up to date with entity changes, or your tests will fail
 * @since 1.0.6-SNAPSHOT
 */

/**
 * create schema or tables
 */
CREATE TABLE user_entity (
	user_uuid       UUID PRIMARY KEY, --     DEFAULT random_uuid(),
	name            VARCHAR2(255) NOT NULL,
	email           VARCHAR2(255) NOT NULL UNIQUE,
	ip              VARCHAR2(255),
	creation_date   DATETIME      NOT NULL DEFAULT now(),
	last_login_date DATETIME      NOT NULL DEFAULT now(),
	version         INTEGER       NOT NULL DEFAULT 1,
	active          BOOLEAN       NOT NULL DEFAULT TRUE,
);

CREATE TABLE tag_entity (
	tag_uuid      UUID PRIMARY KEY, --    DEFAULT random_uuid(),
	version       INTEGER      NOT NULL DEFAULT 1,
	tag_name      VARCHAR(255) NOT NULL CHECK (tag_name IN ('promoted', 'featured')),
	creation_date DATETIME     NOT NULL DEFAULT now(),
	modify_date   DATETIME
);

CREATE TABLE product_entity (
	product_uuid        UUID PRIMARY KEY, --       DEFAULT random_uuid(),
	version             INTEGER        NOT NULL DEFAULT 1,
	name                VARCHAR(255)   NOT NULL,
	description         VARCHAR2(2000) NOT NULL,
	creation_date       DATETIME       NOT NULL DEFAULT now(),
	modify_date         DATETIME,
	active              BOOLEAN        NOT NULL DEFAULT TRUE,

	number_of_downloads BIGINT         NOT NULL DEFAULT 1,
	preview_image       BLOB,
	product_item        CLOB,

	user_uuid           UUID, -- 	tag_uuid            UUID,

	--  one user creates or owns one or many products
	FOREIGN KEY (user_uuid) REFERENCES user_entity (user_uuid),

	// one product has one TAG
	-- 	FOREIGN KEY (tag_uuid) REFERENCES tag_entity (tag_uuid)
);

CREATE TABLE rating_entity (
	rating_uuid        UUID PRIMARY KEY, --       DEFAULT random_uuid(),
	version            INTEGER        NOT NULL DEFAULT 1,
	rating_description VARCHAR2(2000) NOT NULL,
	rating_as_number   INT            NOT NULL,
	creation_date      DATETIME       NOT NULL DEFAULT now(),
	modify_date        DATETIME,
	active             BOOLEAN        NOT NULL DEFAULT TRUE,

	user_uuid          UUID,
	product_uuid       UUID,

	--  one rating is made by one person
	FOREIGN KEY (user_uuid) REFERENCES user_entity (user_uuid),

	// one product has one or many ratings
	FOREIGN KEY (product_uuid) REFERENCES product_entity (product_uuid)
);