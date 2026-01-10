package com.lanchonete.service;

import com.lanchonete.model.dto.request.ProdutoRequest;
import com.lanchonete.model.dto.response.ProdutoResponse;
import com.lanchonete.model.entity.Produto;
import com.lanchonete.model.mapper.ProdutoMapper;
import com.lanchonete.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponse> findAll() {
        return produtoRepository.findByAtivoTrue().stream()
                .map(produtoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProdutoResponse findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return produtoMapper.toResponse(produto);
    }

    @Override
    public ProdutoResponse save(ProdutoRequest request) {
        if (produtoRepository.existsByNome(request.getNome())) {
            throw new RuntimeException("Produto com nome já existe");
        }
        Produto produto = produtoMapper.toEntity(request);
        produto = produtoRepository.save(produto);
        return produtoMapper.toResponse(produto);
    }

    @Override
    public ProdutoResponse update(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoMapper.updateEntity(request, produto);
        produto = produtoRepository.save(produto);
        return produtoMapper.toResponse(produto);
    }

    @Override
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponse> findByCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria).stream()
                .map(produtoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponse> findByNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(produtoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
