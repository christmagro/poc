--liquibase formatted sql

--changeset roland.chelwing:0 logicalFilePath:init-db/0-structure.sql context:dev,qa,eph,stg,prod labels:plng,rdge,afun
--comment DEV-34552: Schema structure

CREATE TABLE IF NOT EXISTS `payment_POC`
(
  `payment_poc_id`    INT UNSIGNED auto_increment PRIMARY KEY,
  `payment_poc_name`  VARCHAR(80) DEFAULT '' NOT NULL
)
engine=innodb
DEFAULT charset=utf8mb4;
