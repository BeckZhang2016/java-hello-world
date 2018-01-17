USE mystest;
CREATE TABLE IF NOT EXISTS user (
  `id`         INT      AUTO_INCREMENT PRIMARY KEY,
  `username`   VARCHAR(20),
  `ecrptypwd`  VARCHAR(200),
  `createdate` DATETIME DEFAULT now()
);

CREATE TABLE IF NOT EXISTS role (
  `id`   INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS department (
  `id`   INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS user_role (
  `userId` INT,
  `roleId` INT
);

CREATE TABLE IF NOT EXISTS user_department (
  `userId`       INT,
  `departmentId` INT
);

