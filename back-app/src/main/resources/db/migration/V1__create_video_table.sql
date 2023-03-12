CREATE TABLE `video` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);