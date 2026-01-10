package com.lanchonete.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EstoqueRequest {
    @NotNull(message = "Produto ID é obrigatório")
    private Long produtoId;

    @NotNull(message = "Quantidade é obrigatória")
    @PositiveOrZero(message = "Quantidade deve ser zero ou positiva")
    private BigDecimal quantidade;

    @PositiveOrZero(message = "Quantidade mínima deve ser zero ou positiva")
    private BigDecimal quantidadeMinima = BigDecimal.ZERO;

    private LocalDate dataValidade;

    private LocalDate dataEntrada;

    private String lote;

    private Boolean ativo = true;
}
