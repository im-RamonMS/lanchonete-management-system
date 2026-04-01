package com.lanchonete.service;

import com.lanchonete.model.dto.request.ReceitaRequest;
import com.lanchonete.model.dto.response.ReceitaResponse;

import java.util.List;

public interface ReceitaService {

    List<ReceitaResponse> findAll();

    ReceitaResponse findById(Long id);

    ReceitaResponse save(Long itemCardapioId, ReceitaRequest request);

    ReceitaResponse update(Long id, ReceitaRequest request);

    void delete(Long id);

    List<ReceitaResponse> findByItemCardapioId(Long itemCardapioId);

    List<ReceitaResponse> findByProdutoId(Long produtoId);
}
