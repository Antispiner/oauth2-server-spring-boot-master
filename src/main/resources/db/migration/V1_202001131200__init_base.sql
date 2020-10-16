DROP TABLE IF EXISTS users;
CREATE TABLE users (
user_id SERIAL NOT NULL ,
email varchar(255) UNIQUE,
first_name varchar(255),
last_name varchar(255),
name varchar(255),
password varchar(255),
verification_key VARCHAR(255) DEFAULT '',
status VARCHAR(255) DEFAULT 'ACTIVE',
created_at TIMESTAMP DEFAULT now(),
last_modified_at TIMESTAMP DEFAULT now(),
restore_key VARCHAR(255) DEFAULT '',
zip_code VARCHAR(255) DEFAULT '',
phone_number VARCHAR(255) DEFAULT '',
race VARCHAR(255) DEFAULT '',
ethnicity VARCHAR(255) DEFAULT '',
verification_code INTEGER DEFAULT 0,
valid_phone_number BOOLEAN DEFAULT FALSE,
gender VARCHAR(255) DEFAULT '',
PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles (
role_id serial NOT NULL,
role varchar(255) DEFAULT NULL,
PRIMARY KEY (role_id)
);


DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
user_id bigint NOT NULL,
role_id int NOT NULL,
PRIMARY KEY (user_id,role_id)
);

DROP TABLE IF EXISTS relatives;
CREATE TABLE relatives (
relative_id SERIAL NOT NULL ,
user_id bigint NOT NULL,
language VARCHAR(10),
email varchar(255) DEFAULT NULL,
phone_number varchar(255) DEFAULT NULL,
contact varchar(255) DEFAULT 'EMAIL',
PRIMARY KEY (relative_id)
);

DROP TABLE IF EXISTS internationalization;
CREATE TABLE internationalization (
internationalization_id SERIAL NOT NULL ,
word_en VARCHAR(255) NOT NULL,
word_ru VARCHAR(255) NOT NULL,
group_enum VARCHAR(255) NOT NULL,
key_word VARCHAR(255) DEFAULT 'word',
PRIMARY KEY (internationalization_id)
);

INSERT INTO users( email, PASSWORD, first_name, last_name, name)
  VALUES ('admin@test.com', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', 'stive', 'test', 'admin');