package com.lanchonete.model.dto.response;

import com.lanchonete.model.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class EstoqueResponse extends BaseDTO {

    private Long produtoId;
    private String produtoNome;
    private BigDecimal quantidade;
    private BigDecimal quantidadeMinima;
    private LocalDate dataValidade;
    private LocalDate dataEntrada;
    private String lote;
    private Boolean ativo;

    // Campos calculados
    private boolean precisaRepor;
    private boolean proximoVencimento;
    private Long diasParaVencimento;
}
