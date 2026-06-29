
DROP DATABASE IF EXISTS ifbank;
CREATE DATABASE ifbank;
USE ifbank;

CREATE TABLE cliente (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         senha VARCHAR(255) NOT NULL,
                         telefone VARCHAR(20),
                         endereco VARCHAR(255),
                         aprovado BOOLEAN DEFAULT FALSE,
                         perfil VARCHAR(50) DEFAULT 'CLIENTE',
                         foto_url VARCHAR(255)
);

CREATE TABLE conta (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       numero_conta VARCHAR(50) NOT NULL UNIQUE,
                       saldo DOUBLE NOT NULL DEFAULT 0.0,
                       status VARCHAR(50) NOT NULL DEFAULT 'ATIVA',
                       cliente_id BIGINT NOT NULL,
                       FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE transferencia (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               valor DOUBLE NOT NULL,
                               data DATETIME NOT NULL,
                               conta_origem_id BIGINT NOT NULL,
                               conta_destino_id BIGINT NOT NULL,
                               FOREIGN KEY (conta_origem_id) REFERENCES conta(id),
                               FOREIGN KEY (conta_destino_id) REFERENCES conta(id)
);

CREATE TABLE movimentacao (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              tipo VARCHAR(100) NOT NULL,
                              valor DOUBLE NOT NULL,
                              data DATETIME NOT NULL,
                              conta_id BIGINT NOT NULL,
                              FOREIGN KEY (conta_id) REFERENCES conta(id)
);

CREATE TABLE investimento (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              tipo VARCHAR(100) NOT NULL,
                              valor DOUBLE NOT NULL,
                              rendimento DOUBLE DEFAULT 0.0,
                              data DATETIME NOT NULL,
                              conta_id BIGINT NOT NULL,
                              FOREIGN KEY (conta_id) REFERENCES conta(id)
);

INSERT INTO cliente (nome, email, senha, telefone, endereco, aprovado, perfil)
VALUES ('Gerente', 'gerente@ifbank.com', '123456', '11999999999', 'Rua X', true, 'GERENTE');