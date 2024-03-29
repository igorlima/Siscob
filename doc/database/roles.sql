CREATE TABLE siscob.users
  (
    USER_NAME VARCHAR(15) NOT NULL,
    USER_PASS VARCHAR(15) NOT NULL,
    PRIMARY KEY (USER_NAME)
  )
ENGINE=InnoDB
DEFAULT CHARSET=latin1;
  
CREATE TABLE siscob.user_roles
  (
    USER_NAME     VARCHAR(15) NOT NULL,
    ROLE_NAME     VARCHAR(200) NOT NULL,
    APPLICATION   VARCHAR(200),
    PRIMARY KEY (USER_NAME, ROLE_NAME, APPLICATION)
  )
ENGINE=InnoDB
DEFAULT CHARSET=latin1;

INSERT INTO `siscob`.`users` (`USER_NAME`,`USER_PASS`)
       VALUES("cadu","123");

INSERT INTO `siscob`.`user_roles` (`USER_NAME`,`ROLE_NAME`,`APPLICATION`)
       VALUES ("cadu","SISCOB+PERMISSAO+usuario+VER","SISCOB");
INSERT INTO `siscob`.`user_roles` (`USER_NAME`,`ROLE_NAME`,`APPLICATION`)
       VALUES ("cadu","SISCOB+PERMISSAO+usuario+EDITAR","SISCOB");
INSERT INTO `siscob`.`user_roles` (`USER_NAME`,`ROLE_NAME`,`APPLICATION`)
       VALUES ("cadu","GRUPO+Membros","SISCOB");