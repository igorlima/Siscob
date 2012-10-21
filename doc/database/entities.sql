DROP TABLE IF EXISTS siscob.usuario;
CREATE TABLE siscob.usuario
  (
    ID              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NOME            VARCHAR(256) NOT NULL,
    ENDERECO        VARCHAR(256),
    CPF             VARCHAR(20),
    TELEFONE        VARCHAR(20),
    ATIVO           BOOLEAN NOT NULL,
    DATAPENALIZACAO DATE
  )
ENGINE=InnoDB
DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS siscob.publicacao;
CREATE TABLE siscob.publicacao
  (
    ID              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    TITULO          VARCHAR(256) NOT NULL,
    EDITORA         VARCHAR(256),
    ANO             VARCHAR(11),
    ATIVO           BOOLEAN NOT NULL,
    TIPO            VARCHAR(20)
  )
ENGINE=InnoDB
DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS siscob.periodico;
CREATE TABLE siscob.periodico
  (
    ID              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NUMEDICAO       INT,
    MES             VARCHAR(256)
  )
ENGINE=InnoDB
DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS siscob.livro;
CREATE TABLE siscob.livro
  (
    ID                INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    AUTORES           VARCHAR(256),
    QTDEXEMPLARES     INT,
    ID_ITEMEMPRESTIMO INT
  )
ENGINE=InnoDB
DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS siscob.itememprestimo;
CREATE TABLE siscob.itememprestimo
  (
    ID              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    ID_PUBLICACAO   INT,
    ID_EMPRESTIMO   INT,
    ATIVO           BOOLEAN NOT NULL,
    DATADEVOLUCAO   DATE
  )
ENGINE=InnoDB
DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS siscob.emprestimo;
CREATE TABLE siscob.emprestimo
  (
    ID              INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    DATAEMPRESTIMO  DATE,
    DATADEVOLUCAO   DATE,
    ATIVO           BOOLEAN NOT NULL,
    ID_USUARIO      INT
  )
ENGINE=InnoDB
DEFAULT CHARSET=latin1;