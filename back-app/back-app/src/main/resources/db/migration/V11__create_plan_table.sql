CREATE TABLE plan (
  id BIGINT NOT NULL,
   price24 INT NULL,
   price48 INT NULL,
   price24premium INT NULL,
   price48premium INT NULL,
   CONSTRAINT pk_plan PRIMARY KEY (id)
);

ALTER TABLE video ADD plan_id BIGINT NULL;