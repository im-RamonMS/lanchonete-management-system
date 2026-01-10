package com.lanchonete.model.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ReceitaResponse {

    private Long id;
    private Long produtoId;
    private String produtoNome;
    private String produtoUnidadeMedida;
    private BigDecimal quantidade;
    private String observacao;
    private Boolean ativo;

    private BigDecimal custoIngrediente;
}