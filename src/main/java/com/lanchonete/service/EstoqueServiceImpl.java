package com.lanchonete.service;

import com.lanchonete.model.dto.request.EstoqueRequest;
import com.lanchonete.model.dto.response.EstoqueResponse;
import com.lanchonete.model.entity.Estoque;
import com.lanchonete.model.mapper.EstoqueMapper;
import com.lanchonete.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EstoqueServiceImpl implements EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper estoqueMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EstoqueResponse> findAll() {
        return estoqueRepository.findByAtivoTrue().stream()
                .map(estoqueMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EstoqueResponse findById(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
        return estoqueMapper.toResponse(estoque);
    }

    @Override
    @Transactional(readOnly = true)
    public EstoqueResponse findByProdutoId(Long produtoId) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new RuntimeException("Estoque para o produto não encontrado"));
        return estoqueMapper.toResponse(estoque);
    }

    @Override
    public EstoqueResponse save(EstoqueRequest request) {
        if (estoqueRepository.findByProdutoId(request.getProdutoId()).isPresent()) {
            throw new RuntimeException("Estoque para este produto já existe");
        }
        Estoque estoque = estoqueMapper.toEntity(request);
        estoque = estoqueRepository.save(estoque);
        return estoqueMapper.toResponse(estoque);
    }

    @Override
    public EstoqueResponse update(Long id, EstoqueRequest request) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
        estoqueMapper.updateEntity(request, estoque);
        estoque = estoqueRepository.save(estoque);
        return estoqueMapper.toResponse(estoque);
    }

    @Override
    public void delete(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
        estoque.setAtivo(false);
        estoqueRepository.save(estoque);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstoqueResponse> findProdutosBaixoEstoque() {
        return estoqueRepository.findProdutosBaixoEstoque().stream()
                .map(estoqueMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void aumentarQuantidade(Long id, Double quantidade) {
        int updated = estoqueRepository.aumentarQuantidade(id, BigDecimal.valueOf(quantidade));
        if (updated == 0) {
            throw new RuntimeException("Falha ao aumentar quantidade do estoque");
        }
    }

    @Override
    public void diminuirQuantidade(Long id, Double quantidade) {
        int updated = estoqueRepository.diminuirQuantidade(id, BigDecimal.valueOf(quantidade));
        if (updated == 0) {
            throw new RuntimeException("Quantidade insuficiente no estoque ou falha na operação");
        }
    }
}
