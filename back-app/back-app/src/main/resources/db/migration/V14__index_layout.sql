CREATE TABLE index_layout (
  id BIGINT NOT NULL,
   priority INT NOT NULL,
   `description` VARCHAR(255) NULL,
   text1 LONGTEXT NULL,
   text2 LONGTEXT NULL,
   text3 LONGTEXT NULL,
   text4 LONGTEXT NULL,
   CONSTRAINT pk_indexlayout PRIMARY KEY (id)
);

CREATE TABLE index_carousel (
  id BIGINT NOT NULL,
   index_layout_id BIGINT NULL,
   image_file_path VARCHAR(255) NULL,
   image_alt VARCHAR(255) NULL,
   CONSTRAINT pk_indexcarousel PRIMARY KEY (id)
);

ALTER TABLE index_carousel ADD CONSTRAINT FK_INDEXCAROUSEL_ON_INDEX_LAYOUT FOREIGN KEY (index_layout_id) REFERENCES index_layout (id);