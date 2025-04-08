CREATE SEQUENCE seq_pessoa_id START 1;
CREATE SEQUENCE seq_foto_id START 1;
CREATE SEQUENCE seq_endereco_id START 1;
CREATE SEQUENCE seq_unidade_id START 1;
CREATE SEQUENCE seq_cidade_id START 1;
CREATE SEQUENCE seq_lotacao_id START 1;
CREATE SEQUENCE seq_usuario_id START 1;

CREATE TABLE cidade (
    cid_id INTEGER DEFAULT nextval('seq_cidade_id') PRIMARY KEY,
    cid_nome VARCHAR(200) NOT NULL,
    cid_uf CHAR(2) NOT NULL
);

CREATE TABLE endereco (
    end_id INTEGER DEFAULT nextval('seq_endereco_id') PRIMARY KEY,
    end_tipo_logradouro VARCHAR(50) NOT NULL,
    end_logradouro VARCHAR(200) NOT NULL,
    end_numero INTEGER NOT NULL,
    end_bairro VARCHAR(100) NOT NULL,
    cid_id INTEGER NOT NULL,
    FOREIGN KEY (cid_id) REFERENCES cidade (cid_id)
);

CREATE TABLE unidade (
    unid_id INTEGER DEFAULT nextval('seq_unidade_id') PRIMARY KEY,
    unid_nome VARCHAR(200) NOT NULL,
    unid_sigla VARCHAR(20) NOT NULL
);

CREATE TABLE unidade_endereco (
    unid_id INTEGER NOT NULL,
    end_id INTEGER NOT NULL,
    PRIMARY KEY (unid_id, end_id),
    FOREIGN KEY (unid_id) REFERENCES unidade (unid_id),
    FOREIGN KEY (end_id) REFERENCES endereco (end_id)
);

CREATE TABLE pessoa (
    pes_id INTEGER DEFAULT nextval('seq_pessoa_id') PRIMARY KEY,
    pes_nome VARCHAR(200) NOT NULL,
    pes_data_nascimento DATE NOT NULL,
    pes_sexo VARCHAR(1) NOT NULL,
    pes_mae VARCHAR(200) NOT NULL,
    pes_pai VARCHAR(200) NULL
);

CREATE TABLE foto_pessoa (
    fp_id INTEGER DEFAULT nextval('seq_foto_id') PRIMARY KEY,
    pes_id INTEGER NOT NULL,
    fp_data DATE NOT NULL,
    fp_bucket VARCHAR(50) NOT NULL,
    fp_hash VARCHAR(50) NOT NULL,
    FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id)
);

CREATE TABLE pessoa_endereco (
    pes_id INTEGER NOT NULL,
    end_id INTEGER NOT NULL,
    PRIMARY KEY (pes_id, end_id),
    FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id),
    FOREIGN KEY (end_id) REFERENCES endereco (end_id)
);

CREATE TABLE servidor_temporario (
    pes_id INTEGER NOT NULL PRIMARY KEY,
    st_data_admissao DATE NOT NULL,
    st_data_demissao DATE NULL,
    FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id)
);

CREATE TABLE servidor_efetivo (
    pes_id INTEGER NOT NULL PRIMARY KEY,
    se_matricula VARCHAR(20) NOT NULL,
    FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id)
);

CREATE TABLE lotacao (
    lot_id INTEGER DEFAULT nextval('seq_lotacao_id') PRIMARY KEY,
    pes_id INTEGER NOT NULL,
    unid_id INTEGER NOT NULL,
    lot_data_lotacao DATE NOT NULL,
    lot_data_remocao DATE NULL,
    lot_portaria VARCHAR(100) NOT NULL,
    FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id),
    FOREIGN KEY (unid_id) REFERENCES unidade (unid_id)
);

CREATE TABLE usuario (
    usr_id INTEGER DEFAULT nextval('seq_usuario_id') NOT NULL,
    usr_matricula VARCHAR(20) NOT NULL,
    usr_senha VARCHAR(255) NOT NULL,
    usr_ativo BOOLEAN DEFAULT TRUE NOT NULL,
    se_id INTEGER,
    CONSTRAINT pk_usuario PRIMARY KEY (usr_id),
    CONSTRAINT uk_usuario_matricula UNIQUE (usr_matricula),
    FOREIGN KEY (se_id) REFERENCES servidor_efetivo (pes_id)
);

CREATE INDEX idx_pessoa_nome ON pessoa (pes_nome);
CREATE INDEX idx_unidade_nome ON unidade (unid_nome);
CREATE INDEX idx_lotacao_pes_id ON lotacao (pes_id);
CREATE INDEX idx_lotacao_unid_id ON lotacao (unid_id);
CREATE INDEX idx_pessoa_endereco_pes_id ON pessoa_endereco (pes_id);
CREATE INDEX idx_pessoa_endereco_end_id ON pessoa_endereco (end_id);
CREATE INDEX idx_endereco_cid_id ON endereco (cid_id);
CREATE INDEX idx_usuario_matricula ON usuario(usr_matricula);
CREATE INDEX idx_usuario_servidor_efetivo ON usuario(se_id);

COMMENT ON TABLE pessoa IS 'Armazena informações pessoais básicas';
COMMENT ON TABLE foto_pessoa IS 'Armazena informações sobre fotos das pessoas';
COMMENT ON TABLE endereco IS 'Armazena informações de endereços';
COMMENT ON TABLE pessoa_endereco IS 'Relaciona pessoas a seus endereços';
COMMENT ON TABLE servidor_temporario IS 'Armazena informações específicas de servidores temporários';
COMMENT ON TABLE servidor_efetivo IS 'Armazena informações específicas de servidores efetivos';
COMMENT ON TABLE lotacao IS 'Registra a lotação de servidores em unidades';
COMMENT ON TABLE unidade IS 'Armazena informações sobre unidades organizacionais';
COMMENT ON TABLE unidade_endereco IS 'Relaciona unidades a seus endereços';
COMMENT ON TABLE usuario IS 'Tabela que armazena informações de usuários do sistema';

ALTER SEQUENCE seq_pessoa_id OWNED BY pessoa.pes_id;
ALTER SEQUENCE seq_foto_id OWNED BY foto_pessoa.fp_id;
ALTER SEQUENCE seq_endereco_id OWNED BY endereco.end_id;
ALTER SEQUENCE seq_unidade_id OWNED BY unidade.unid_id;
ALTER SEQUENCE seq_cidade_id OWNED BY cidade.cid_id;
ALTER SEQUENCE seq_lotacao_id OWNED BY lotacao.lot_id;
ALTER SEQUENCE seq_usuario_id OWNED BY usuario.usr_id;