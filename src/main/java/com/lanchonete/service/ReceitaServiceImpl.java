package com.lanchonete.service;

import com.lanchonete.model.dto.request.ReceitaRequest;
import com.lanchonete.model.dto.response.ReceitaResponse;
import com.lanchonete.model.entity.Receita;
import com.lanchonete.model.entity.ItemCardapio;
import com.lanchonete.model.mapper.ReceitaMapper;
import com.lanchonete.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReceitaServiceImpl implements ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ReceitaMapper receitaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ReceitaResponse> findAll() {
        return receitaRepository.findAll().stream()
                .map(receitaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReceitaResponse findById(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
        return receitaMapper.toResponse(receita);
    }

    @Override
    public ReceitaResponse save(Long itemCardapioId, ReceitaRequest request) {
        if (receitaRepository.existsByItemCardapioIdAndProdutoId(itemCardapioId, request.getProdutoId())) {
            throw new RuntimeException("Receita para este item e produto já existe");
        }
        Receita receita = receitaMapper.toEntity(request);
        // Set the itemCardapio
        ItemCardapio itemCardapio = new ItemCardapio();
        itemCardapio.setId(itemCardapioId);
        receita.setItemCardapio(itemCardapio);
        receita = receitaRepository.save(receita);
        return receitaMapper.toResponse(receita);
    }

    @Override
    public ReceitaResponse update(Long id, ReceitaRequest request) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
        receitaMapper.updateEntity(request, receita);
        receita = receitaRepository.save(receita);
        return receitaMapper.toResponse(receita);
    }

    @Override
    public void delete(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
        receita.setAtivo(false);
        receitaRepository.save(receita);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceitaResponse> findByItemCardapioId(Long itemCardapioId) {
        return receitaRepository.findByItemCardapioId(itemCardapioId).stream()
                .map(receitaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceitaResponse> findByProdutoId(Long produtoId) {
        return receitaRepository.findByProdutoId(produtoId).stream()
                .map(receitaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
