package com.lanchonete.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ReceitaRequest {

    @NotNull(message = "Produto ID é obrigatório")
    private Long produtoId;

    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    private BigDecimal quantidade;

    private String observacao;

    private Boolean ativo = true;
}