package com.lanchonete.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false, unique = true)
    private Produto produto;

    @Column(name = "quantidade", nullable = false, precision = 10, scale = 3)
    private BigDecimal quantidade;

    @Column(name = "quantidade_minima", nullable = false, precision = 10, scale = 3)
    @Builder.Default
    private BigDecimal quantidadeMinima = BigDecimal.ZERO;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "lote", length = 50)
    private String lote;

    @Column(name = "ativo", nullable = false)
    @Builder.Default
    private Boolean ativo = true;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
        if (dataEntrada == null) {
            dataEntrada = LocalDate.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }


    public boolean precisaRepor() {
        return quantidade.compareTo(quantidadeMinima) <= 0;
    }


    public boolean proximoVencimento(int diasAntecedencia) {
        if (dataValidade == null) return false;
        LocalDate hoje = LocalDate.now();
        LocalDate dataAlerta = dataValidade.minusDays(diasAntecedencia);
        return !hoje.isAfter(dataAlerta) && !hoje.isBefore(dataAlerta.minusDays(7));
    }
}