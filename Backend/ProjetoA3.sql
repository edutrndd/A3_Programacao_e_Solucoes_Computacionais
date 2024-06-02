CREATE TABLE usuario_adm (
    id_adm SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INTEGER NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    sexo CHAR(1) NOT NULL,
    senha VARCHAR(100) NOT NULL
); 

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INTEGER NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    sexo CHAR(1) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

CREATE TABLE projetos (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_inicio DATE,
    data_fim DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    projeto_id INTEGER NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_conclusao TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (projeto_id) REFERENCES projetos(id)
);