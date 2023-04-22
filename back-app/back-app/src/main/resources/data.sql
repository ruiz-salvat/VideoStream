CREATE TABLE IF NOT EXISTS hibernate_sequence (
  next_val bigint DEFAULT NULL
);

INSERT INTO `video_stream`.`hibernate_sequence`
(`next_val`)
VALUES
(1);