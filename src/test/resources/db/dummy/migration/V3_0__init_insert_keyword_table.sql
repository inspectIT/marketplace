CREATE TABLE keyword_entity (
	keyword_uuid  UUID PRIMARY KEY, --     DEFAULT random_uuid(),
	name          VARCHAR2(255) NOT NULL,
	alias         VARCHAR2(255) NOT NULL,

	creation_user VARCHAR2(255) NOT NULL DEFAULT 'db-user',
	creation_date DATETIME      NOT NULL DEFAULT now(),
	version       INTEGER       NOT NULL DEFAULT 1,
	active        BOOLEAN       NOT NULL DEFAULT TRUE,
);

/**
 * many to many helper table
 */
CREATE TABLE keyword_product (
	product_uuid UUID NOT NULL,
	keyword_uuid UUID NOT NULL,

	FOREIGN KEY (product_uuid) REFERENCES product_entity (product_uuid),
	FOREIGN KEY (keyword_uuid) REFERENCES keyword_entity (keyword_uuid)
);

/**
 * insert
 */
INSERT INTO keyword_entity (keyword_uuid, alias, name, creation_user) VALUES ('33c8d8e590964d7d814f6c1ed0601bcd', 'spring', 'Spring Framework', 'nik u');
INSERT INTO keyword_entity (keyword_uuid, alias, name, creation_user) VALUES ('7aad78adb962492a9ab3aff74218159a', 'hibernate', 'Hibernate ORM', 'nik u');
INSERT INTO keyword_entity (keyword_uuid, alias, name, creation_user) VALUES ('63e2ff9b05fc4699852a27faf1d834cd', 'jee', 'Java Enterprise Edition', 'nik u');
INSERT INTO keyword_entity (keyword_uuid, alias, name, creation_user) VALUES ('f4fb280ba9214486a0c60c94d3d157a4', 'jpa', 'Java Persistence API', 'nik u');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('8650caf1f023480895f7322af55fb163', '33c8d8e590964d7d814f6c1ed0601bcd');
INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('8650caf1f023480895f7322af55fb163', '7aad78adb962492a9ab3aff74218159a');
INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('8650caf1f023480895f7322af55fb163', '63e2ff9b05fc4699852a27faf1d834cd');
INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('8650caf1f023480895f7322af55fb163', 'f4fb280ba9214486a0c60c94d3d157a4');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('14144bb2892f4657bcd09ead9066f92a', '33c8d8e590964d7d814f6c1ed0601bcd');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('a625e6f431ea49beb71eeee54e283c43', '7aad78adb962492a9ab3aff74218159a');
INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('a625e6f431ea49beb71eeee54e283c43', '63e2ff9b05fc4699852a27faf1d834cd');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('f9e90ce0a0e94296868b05314cb60b5a', 'f4fb280ba9214486a0c60c94d3d157a4');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('4c049192929d4a498f17c285928fc769', '33c8d8e590964d7d814f6c1ed0601bcd');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('b90bb1f16f7e40a786a33675d4dea7c5', '7aad78adb962492a9ab3aff74218159a');
INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('b90bb1f16f7e40a786a33675d4dea7c5', '63e2ff9b05fc4699852a27faf1d834cd');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('0d36b243aa29407e89d6666e164c5118', 'f4fb280ba9214486a0c60c94d3d157a4');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('6370604e1d584e5bb1b297fd54fb59b5', '33c8d8e590964d7d814f6c1ed0601bcd');

INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('105b789e84c545bf8722108533a81530', '7aad78adb962492a9ab3aff74218159a');
INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('105b789e84c545bf8722108533a81530', '63e2ff9b05fc4699852a27faf1d834cd');
INSERT INTO keyword_product (product_uuid, keyword_uuid) VALUES ('105b789e84c545bf8722108533a81530', 'f4fb280ba9214486a0c60c94d3d157a4');