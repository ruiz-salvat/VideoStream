CREATE TABLE `category` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `video` ADD `category_id` bigint;

ALTER TABLE `video`
ADD FOREIGN KEY (category_id) REFERENCES category(id);