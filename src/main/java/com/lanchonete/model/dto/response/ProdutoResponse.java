package com.lanchonete.model.dto.response;

import com.lanchonete.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProdutoResponse extends BaseDTO {

    private String nome;
    private String descricao;
    private String unidadeMedida;
    private String categoria;
    private BigDecimal custoUnitario;
    private Boolean ativo;

    private BigDecimal quantidadeEmEstoque;
    private LocalDate dataValidade;
    private Boolean precisaRepor;

}
