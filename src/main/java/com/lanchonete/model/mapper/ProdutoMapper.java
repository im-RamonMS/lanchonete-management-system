package com.lanchonete.model.mapper;

import com.lanchonete.model.dto.request.ProdutoRequest;
import com.lanchonete.model.dto.response.ProdutoResponse;
import com.lanchonete.model.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    // Entity → Response
    @Mapping(target = "quantidadeEmEstoque", ignore = true)
    @Mapping(target = "dataValidade", ignore = true)
    @Mapping(target = "precisaRepor", ignore = true)
    ProdutoResponse toResponse(Produto produto);

    // Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    Produto toEntity(ProdutoRequest request);

    // Update Entity from Request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    void updateEntity(ProdutoRequest request, @org.mapstruct.MappingTarget Produto produto);
}