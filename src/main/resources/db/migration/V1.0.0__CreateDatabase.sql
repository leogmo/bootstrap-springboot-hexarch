CREATE TABLE `empresas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `razaosocial` varchar(255) NOT NULL,
  `cnpj` varchar(45) NOT NULL,
  `inscmunicipal` varchar(45) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `cep` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_latvian_ci;


CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `empresaid` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_empresas` (`empresaid`),
  CONSTRAINT `fk_users_empresas` FOREIGN KEY (`empresaid`) REFERENCES `empresas` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_latvian_ci;


CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `users_roles` (
  `users_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  KEY `fk_users_roles_roles` (`roles_id`),
  KEY `fk_users_roles_users` (`users_id`),
  CONSTRAINT `fk_users_roles_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE cascade,
  CONSTRAINT `fk_users_roles_roles` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


insert into roles(name) values ('ADMIN'), ('USER');
insert into empresas(id, razaosocial, cnpj, inscmunicipal, endereco, bairro, cidade, estado, cep)
values (1, 'lc systems', '7776867','898778879', 'asdfasdfasd', 'dfdsfsd', 'dsfsdafasd', 'asdfdsaf', '32423423');