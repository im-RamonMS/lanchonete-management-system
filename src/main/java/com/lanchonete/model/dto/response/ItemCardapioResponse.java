package com.lanchonete.model.dto.response;

import com.lanchonete.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItemCardapioResponse extends BaseDTO {

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private Integer tempoPreparoMinutos;
    private Boolean ativo;
    private Boolean destaque;


    private BigDecimal custoEstimado;
    private BigDecimal margemLucro;

    // lista de ingredientes
    private List<ReceitaResponse> ingredientes = new ArrayList<>();
}