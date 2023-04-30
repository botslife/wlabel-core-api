CREATE DATABASE IF NOT EXISTS core;

ALTER DATABASE core
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON core2.* TO 'coreuser'@'%' IDENTIFIED BY 'corepwd';