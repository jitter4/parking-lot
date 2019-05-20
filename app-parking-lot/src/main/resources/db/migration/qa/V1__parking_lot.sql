--drop table if exists flyway_schema_history;
--drop table if exists users;
--drop table if exists roles;
--drop table if exists users_roles;
--drop table if exists spots;
--drop table if exists vehicles;



CREATE TABLE `users` (
  `id`              bigint(20)      NOT NULL        AUTO_INCREMENT,
  `created_on`      datetime        DEFAULT NULL,
  `updated_on`      datetime        DEFAULT NULL,
  `version`         bigint(20)      DEFAULT NULL,
  `email`           varchar(40)     DEFAULT NULL,
  `name`            varchar(40)     DEFAULT NULL,
  `password`        varchar(100)    DEFAULT NULL,
  `username`        varchar(15)     DEFAULT NULL,
  PRIMARY KEY       (`id`),
  UNIQUE KEY        `UKr43af9ap4edm43mmtq01oddj6`   (`username`),
  UNIQUE KEY        `UK6dotkott2kjsp8vw4d0m25fb7`   (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `roles` (
  `id`              bigint(20)      NOT NULL        AUTO_INCREMENT,
  `name`            varchar(60)     DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO roles(name)
    VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');



CREATE TABLE `users_roles` (
  `user_id`         bigint(20)      NOT NULL,
  `role_id`         bigint(20)      NOT NULL,
  PRIMARY KEY       (`user_id`,`role_id`),
  KEY               `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT        `FK2o0jvgh89lemvvo17cbqvdxaa`
  FOREIGN KEY       (`user_id`)     REFERENCES `users` (`id`),
  CONSTRAINT        `FKj6m8fwv7oqv74fcehir1a9ffy`
  FOREIGN KEY       (`role_id`)     REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `vehicles` (
  `id`              bigint(20)      NOT NULL        AUTO_INCREMENT,
  `created_on`      datetime        DEFAULT NULL,
  `updated_on`      datetime        DEFAULT NULL,
  `version`         bigint(20)      DEFAULT NULL,
  `color`           varchar(255)    NOT NULL,
  `in_time`         datetime        NOT NULL,
  `out_time`        datetime        DEFAULT NULL,
  `vehicle_number`  varchar(255)    NOT NULL,
  `type`            varchar(255)    NOT NULL,
  `spot_id`         bigint(20)      DEFAULT NULL,
  PRIMARY KEY       (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `spots` (
  `id`              bigint(20)      NOT NULL         AUTO_INCREMENT,
  `created_on`      datetime        DEFAULT NULL,
  `updated_on`      datetime        DEFAULT NULL,
  `version`         bigint(20)      DEFAULT NULL,
  `level`           int(11)         NOT NULL,
  `pos`             int(11)         NOT NULL,
  `type`            varchar(255)    DEFAULT NULL,
  `vehicle_id`      bigint(20)      DEFAULT NULL,
  PRIMARY KEY       (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE `vehicles`
    ADD CONSTRAINT  FK_Spots
    FOREIGN KEY     (`spot_id`)     REFERENCES      `spots`     (`id`);



ALTER TABLE `spots`
    ADD CONSTRAINT  FK_Vehicles
    FOREIGN KEY     (`vehicle_id`)  REFERENCES      `vehicles`  (`id`);