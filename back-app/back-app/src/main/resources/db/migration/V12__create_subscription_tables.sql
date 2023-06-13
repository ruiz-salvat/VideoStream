CREATE TABLE subscription_type (
  id BIGINT NOT NULL,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_subscriptiontype PRIMARY KEY (id)
);

CREATE TABLE subscription (
  id BIGINT NOT NULL,
   last_payment_timestamp BIGINT NULL,
   valid_until_timestamp BIGINT NULL,
   subscription_type_id BIGINT NULL,
   CONSTRAINT pk_subscription PRIMARY KEY (id)
);

ALTER TABLE subscription ADD CONSTRAINT FK_SUBSCRIPTION_ON_SUBSCRIPTION_TYPE FOREIGN KEY (subscription_type_id) REFERENCES subscription_type (id);

ALTER TABLE application_user ADD subscription_id BIGINT NULL;