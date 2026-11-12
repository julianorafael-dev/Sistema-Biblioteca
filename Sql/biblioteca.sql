-- Exercicio 2

CREATE TABLE item (
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo      VARCHAR(20)  NOT NULL UNIQUE,
    titulo      VARCHAR(100) NOT NULL,
    tipo        VARCHAR(10)  NOT NULL CHECK (tipo IN ('LIVRO', 'REVISTA')),
    autor       VARCHAR(100),                -- revistas costumam não ter autor
    edicao      INT,                         -- pode ficar vazio
    disponivel  BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL,
    tipo         VARCHAR(10)  NOT NULL CHECK (tipo IN ('ALUNO', 'PROFESSOR')),
    limite_itens INT          NOT NULL CHECK (limite_itens > 0)
);

CREATE TABLE emprestimo (
    id                      INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id                 INT  NOT NULL REFERENCES item(id),
    usuario_id              INT  NOT NULL REFERENCES usuario(id),
    data_retirada           DATE NOT NULL,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao          DATE,            -- sem NOT NULL: vazia até o item voltar
    valor_multa             NUMERIC(8,2) NOT NULL DEFAULT 0 CHECK (valor_multa >= 0)
);


-- Dados de teste

INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
    ('L1', 'O Senhor dos Anéis', 'LIVRO',  'J. R. R. Tolkien', 1,   TRUE),
    ('L2', 'Harry Potter e a Pedra Filosofal', 'LIVRO', 'J. K. Rowling', 1, TRUE),
    ('R1', 'Superbikes',                'REVISTA', NULL,           400, FALSE),  -- está emprestada
    ('R2', 'Supercarros',               'REVISTA', NULL,           300, TRUE);

INSERT INTO usuario (nome, tipo, limite_itens) VALUES
    ('Agatha',    'ALUNO',     3),
    ('Henri', 'PROFESSOR', 5);


INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES (
    (SELECT id FROM item    WHERE codigo = 'L1'),
    (SELECT id FROM usuario WHERE nome   = 'Agatha'),
    '2026-10-01', '2026-10-15', '2026-10-18', 1.50
);


INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista)
VALUES (
    (SELECT id FROM item    WHERE codigo = 'R1'),
    (SELECT id FROM usuario WHERE nome   = 'Henri'),
    '2026-11-05', '2026-11-12'
);
