CREATE TABLE `usuario` (
  `id_usuario` int PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(100),
  `cpf` varchar(14) UNIQUE,
  `email` varchar(100) UNIQUE,
  `senha` varchar(255),
  `telefone` varchar(20),
  `endereco_id` varchar(255),
  `numero_res` varchar(4),
  `foto` varchar(255),
  `status` varchar(20),
  `data_cadastro` datetime
);

CREATE TABLE `conta` (
  `id_conta` int PRIMARY KEY AUTO_INCREMENT,
  `numero_conta` varchar(20) UNIQUE,
  `saldo` decimal(15,2),
  `status` varchar(20),
  `data_abertura` datetime,
  `id_usuario` int NOT NULL,
  `id_gerente_aprovador` int
);

CREATE TABLE `movimentacao` (
  `id_movimentacao` int PRIMARY KEY AUTO_INCREMENT,
  `tipo` varchar(50),
  `valor` decimal(15,2),
  `data_hora` datetime,
  `descricao` varchar(255),
  `id_conta` int NOT NULL
);

CREATE TABLE `transferencia` (
  `id_transferencia` int PRIMARY KEY AUTO_INCREMENT,
  `valor` decimal(15,2),
  `data_hora` datetime,
  `descricao` varchar(255),
  `situacao` varchar(20),
  `id_conta_origem` int NOT NULL,
  `id_conta_destino` int NOT NULL
);

CREATE TABLE `investimento` (
  `id_investimento` int PRIMARY KEY AUTO_INCREMENT,
  `tipo` varchar(50),
  `valor_aplicado` decimal(15,2),
  `data_aplicacao` datetime,
  `rentabilidade` decimal(5,2),
  `id_conta` int NOT NULL
);

CREATE TABLE `token_reset_senha` (
  `id_token` int PRIMARY KEY AUTO_INCREMENT,
  `token` varchar(255),
  `data_expiracao` datetime,
  `utilizado` boolean,
  `id_usuario` int NOT NULL
);

CREATE TABLE `Endereço` (
  `id_end` int PRIMARY KEY,
  `log` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `bairro` varchar(255) NOT NULL,
  `cep` int NOT NULL
);

CREATE TABLE `cliente` (
  `id_cliente` int PRIMARY KEY
);

CREATE TABLE `gerente` (
  `id_gerente` int PRIMARY KEY,
  `matricula` int UNIQUE
);

ALTER TABLE `conta` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

ALTER TABLE `conta` ADD FOREIGN KEY (`id_gerente_aprovador`) REFERENCES `gerente` (`matricula`);

ALTER TABLE `movimentacao` ADD FOREIGN KEY (`id_conta`) REFERENCES `conta` (`id_conta`);

ALTER TABLE `investimento` ADD FOREIGN KEY (`id_conta`) REFERENCES `conta` (`id_conta`);

ALTER TABLE `transferencia` ADD FOREIGN KEY (`id_conta_origem`) REFERENCES `conta` (`id_conta`);

ALTER TABLE `transferencia` ADD FOREIGN KEY (`id_conta_destino`) REFERENCES `conta` (`id_conta`);

ALTER TABLE `token_reset_senha` ADD FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

ALTER TABLE `usuario` ADD FOREIGN KEY (`endereco_id`) REFERENCES `Endereço` (`id_end`);

ALTER TABLE `cliente` ADD FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`);

ALTER TABLE `gerente` ADD FOREIGN KEY (`id_gerente`) REFERENCES `usuario` (`id_usuario`);
