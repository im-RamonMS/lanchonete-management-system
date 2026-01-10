package com.lanchonete.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "itens_cardapio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "tempo_preparo_minutos")
    private Integer tempoPreparoMinutos;

    @Column(name = "ativo", nullable = false)
    @Builder.Default
    private Boolean ativo = true;

    @Column(name = "destaque")
    @Builder.Default
    private Boolean destaque = false;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "itemCardapio", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Receita> ingredientes = new ArrayList<>();

    // Na classe ItemCardapio, adicionar:

    // Método auxiliar para adicionar ingredientes
    public void adicionarIngrediente(Produto produto, BigDecimal quantidade, String observacao) {
        Receita receita = Receita.builder()
                .itemCardapio(this)
                .produto(produto)
                .quantidade(quantidade)
                .observacao(observacao)
                .build();
        ingredientes.add(receita);
    }

    // Método para remover ingrediente
    public void removerIngrediente(Produto produto) {
        ingredientes.removeIf(receita -> receita.getProduto().equals(produto));
    }

    // Calcular custo total do item
    public BigDecimal calcularCustoTotal() {
        if (ingredientes == null || ingredientes.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return ingredientes.stream()
                .filter(Receita::getAtivo)
                .map(Receita::calcularCusto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Calcular margem de lucro
    public BigDecimal calcularMargemLucro() {
        BigDecimal custo = calcularCustoTotal();
        if (custo.equals(BigDecimal.ZERO) || preco == null) {
            return BigDecimal.ZERO;
        }
        return preco.subtract(custo);
    }

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
}
