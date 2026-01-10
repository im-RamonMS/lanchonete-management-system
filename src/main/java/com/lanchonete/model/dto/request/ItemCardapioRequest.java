package com.lanchonete.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemCardapioRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(max = 500, message = "Descrição muito longa")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    private BigDecimal preco;

    @Size(max = 50, message = "Categoria muito longa")
    private String categoria;

    @Positive(message = "Tempo de preparo deve ser positivo")
    private Integer tempoPreparoMinutos;

    private Boolean ativo = true;
    private Boolean destaque = false;


    private List<ReceitaRequest> ingredientes = new ArrayList<>();
}