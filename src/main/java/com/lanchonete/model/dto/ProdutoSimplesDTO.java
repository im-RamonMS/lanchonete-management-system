package com.lanchonete.model.dto;

import lombok.Data;

@Data
public class ProdutoSimplesDTO {
    private Long id;
    private String nome;
    private String unidadeMedida;
    private Boolean ativo;
}