package com.lanchonete.service;

import com.lanchonete.model.dto.request.ProdutoRequest;
import com.lanchonete.model.dto.response.ProdutoResponse;

import java.util.List;

public interface ProdutoService {

    List<ProdutoResponse> findAll();

    ProdutoResponse findById(Long id);

    ProdutoResponse save(ProdutoRequest request);

    ProdutoResponse update(Long id, ProdutoRequest request);

    void delete(Long id);

    List<ProdutoResponse> findByCategoria(String categoria);

    List<ProdutoResponse> findByNome(String nome);
}
