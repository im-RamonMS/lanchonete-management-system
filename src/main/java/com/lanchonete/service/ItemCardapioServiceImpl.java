package com.lanchonete.service;

import com.lanchonete.model.dto.request.ItemCardapioRequest;
import com.lanchonete.model.dto.response.ItemCardapioResponse;
import com.lanchonete.model.entity.ItemCardapio;
import com.lanchonete.model.mapper.ItemCardapioMapper;
import com.lanchonete.repository.ItemCardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemCardapioServiceImpl implements ItemCardapioService {

    private final ItemCardapioRepository itemCardapioRepository;
    private final ItemCardapioMapper itemCardapioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ItemCardapioResponse> findAll() {
        return itemCardapioRepository.findByAtivoTrue().stream()
                .map(itemCardapioMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ItemCardapioResponse findById(Long id) {
        ItemCardapio item = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));
        return itemCardapioMapper.toResponse(item);
    }

    @Override
    public ItemCardapioResponse save(ItemCardapioRequest request) {
        if (itemCardapioRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Item do cardápio com nome já existe");
        }
        ItemCardapio item = itemCardapioMapper.toEntity(request);
        item = itemCardapioRepository.save(item);
        return itemCardapioMapper.toResponse(item);
    }

    @Override
    public ItemCardapioResponse update(Long id, ItemCardapioRequest request) {
        ItemCardapio item = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));
        itemCardapioMapper.updateEntity(request, item);
        item = itemCardapioRepository.save(item);
        return itemCardapioMapper.toResponse(item);
    }

    @Override
    public void delete(Long id) {
        ItemCardapio item = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));
        item.setAtivo(false);
        itemCardapioRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemCardapioResponse> findByCategoria(String categoria) {
        return itemCardapioRepository.findByCategoria(categoria).stream()
                .map(itemCardapioMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemCardapioResponse> findDestaques() {
        return itemCardapioRepository.findByDestaqueTrueAndAtivoTrue().stream()
                .map(itemCardapioMapper::toResponse)
                .collect(Collectors.toList());
    }
}
