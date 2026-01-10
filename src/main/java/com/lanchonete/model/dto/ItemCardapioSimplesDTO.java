package com.lanchonete.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemCardapioSimplesDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private Boolean ativo;
}