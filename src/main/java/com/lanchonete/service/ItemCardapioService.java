package com.lanchonete.service;

import com.lanchonete.model.dto.request.ItemCardapioRequest;
import com.lanchonete.model.dto.response.ItemCardapioResponse;

import java.util.List;

public interface ItemCardapioService {

    List<ItemCardapioResponse> findAll();

    ItemCardapioResponse findById(Long id);

    ItemCardapioResponse save(ItemCardapioRequest request);

    ItemCardapioResponse update(Long id, ItemCardapioRequest request);

    void delete(Long id);

    List<ItemCardapioResponse> findByCategoria(String categoria);

    List<ItemCardapioResponse> findDestaques();
}
