package com.lanchonete.service;

import com.lanchonete.model.dto.request.EstoqueRequest;
import com.lanchonete.model.dto.response.EstoqueResponse;

import java.util.List;

public interface EstoqueService {

    List<EstoqueResponse> findAll();

    EstoqueResponse findById(Long id);

    EstoqueResponse findByProdutoId(Long produtoId);

    EstoqueResponse save(EstoqueRequest request);

    EstoqueResponse update(Long id, EstoqueRequest request);

    void delete(Long id);

    List<EstoqueResponse> findProdutosBaixoEstoque();

    void aumentarQuantidade(Long id, Double quantidade);

    void diminuirQuantidade(Long id, Double quantidade);
}
