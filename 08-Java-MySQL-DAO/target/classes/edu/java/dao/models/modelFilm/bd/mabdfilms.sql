CREATE DATABASE IF NOT EXISTS mabdfilms;
USE mabdfilms;

CREATE TABLE films (
  idf int(11) NOT NULL AUTO_INCREMENT,
  titre varchar(80) NOT NULL,
  duree int(11) NOT NULL,
  res varchar(40) NOT NULL,
  pochette varchar(256) NOT NULL,
  PRIMARY KEY(idf)
);
