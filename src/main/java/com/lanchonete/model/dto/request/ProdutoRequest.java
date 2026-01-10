package com.lanchonete.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProdutoRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(max = 255, message = "Descrição muito longa")
    private String descricao;

    @NotBlank(message = "Unidade de medida é obrigatória")
    @Size(max = 20, message = "Unidade de medida muito longa")
    private String unidadeMedida;

    @Size(max = 50, message = "Categoria muito longa")
    private String categoria;

    @PositiveOrZero(message = "Custo unitário deve ser zero ou positivo")
    private BigDecimal custoUnitario;

    private Boolean ativo = true;
}
