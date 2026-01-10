-- Tabela de Produtos (ingredientes)
CREATE TABLE IF NOT EXISTS produtos (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        nome VARCHAR(100) NOT NULL,
                                        descricao VARCHAR(255),
                                        unidade_medida VARCHAR(20) NOT NULL,
                                        categoria VARCHAR(50),
                                        custo_unitario DECIMAL(10,2),
                                        ativo BOOLEAN NOT NULL DEFAULT TRUE,
                                        data_cadastro TIMESTAMP,
                                        data_atualizacao TIMESTAMP
);

-- Tabela de Estoque
CREATE TABLE IF NOT EXISTS estoque (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       produto_id BIGINT NOT NULL UNIQUE,
                                       quantidade DECIMAL(10,3) NOT NULL,
                                       quantidade_minima DECIMAL(10,3) NOT NULL DEFAULT 0,
                                       data_validade DATE,
                                       data_entrada DATE NOT NULL,
                                       lote VARCHAR(50),
                                       ativo BOOLEAN NOT NULL DEFAULT TRUE,
                                       data_cadastro TIMESTAMP,
                                       data_atualizacao TIMESTAMP,
                                       FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

-- Tabela de Itens do Cardápio
CREATE TABLE IF NOT EXISTS itens_cardapio (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              nome VARCHAR(100) NOT NULL,
                                              descricao VARCHAR(500),
                                              preco DECIMAL(10,2) NOT NULL,
                                              categoria VARCHAR(50),
                                              tempo_preparo_minutos INT,
                                              ativo BOOLEAN NOT NULL DEFAULT TRUE,
                                              destaque BOOLEAN DEFAULT FALSE,
                                              data_cadastro TIMESTAMP,
                                              data_atualizacao TIMESTAMP
);

-- Tabela de Receitas (ingredientes por item)
CREATE TABLE IF NOT EXISTS receitas (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        item_cardapio_id BIGINT NOT NULL,
                                        produto_id BIGINT NOT NULL,
                                        quantidade DECIMAL(10,3) NOT NULL,
                                        observacao VARCHAR(200),
                                        ativo BOOLEAN NOT NULL DEFAULT TRUE,
                                        FOREIGN KEY (item_cardapio_id) REFERENCES itens_cardapio(id) ON DELETE CASCADE,
                                        FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE,
                                        UNIQUE(item_cardapio_id, produto_id)
);


CREATE INDEX idx_produtos_categoria ON produtos(categoria);
CREATE INDEX idx_produtos_ativo ON produtos(ativo);
CREATE INDEX idx_estoque_validade ON estoque(data_validade);
CREATE INDEX idx_itens_cardapio_categoria ON itens_cardapio(categoria);
CREATE INDEX idx_itens_cardapio_ativo ON itens_cardapio(ativo);
CREATE INDEX idx_receitas_item ON receitas(item_cardapio_id);
CREATE INDEX idx_receitas_produto ON receitas(produto_id);