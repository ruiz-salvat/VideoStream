ALTER TABLE video
RENAME COLUMN file_path TO video_file_path;

ALTER TABLE `video` ADD `image_file_path` varchar(255) NOT NULL;