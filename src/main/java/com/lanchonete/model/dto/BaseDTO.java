package com.lanchonete.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {
    private Long id;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
