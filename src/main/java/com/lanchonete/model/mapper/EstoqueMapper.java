package com.lanchonete.model.mapper;

import com.lanchonete.model.dto.request.EstoqueRequest;
import com.lanchonete.model.dto.response.EstoqueResponse;
import com.lanchonete.model.entity.Estoque;
import com.lanchonete.model.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {

    EstoqueMapper INSTANCE = Mappers.getMapper(EstoqueMapper.class);

    // Entity → Response
    @Mapping(source = "produto.id", target = "produtoId")
    @Mapping(source = "produto.nome", target = "produtoNome")
    @Mapping(target = "precisaRepor", expression = "java(estoque.precisaRepor())")
    @Mapping(target = "proximoVencimento", expression = "java(estoque.proximoVencimento(7))")
    @Mapping(target = "diasParaVencimento", expression = "java(calcularDiasParaVencimento(estoque.getDataValidade()))")
    EstoqueResponse toResponse(Estoque estoque);

    // Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoIdToProduto")
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "ativo", defaultValue = "true")
    Estoque toEntity(EstoqueRequest request);

    // Update Entity from Request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoIdToProduto")
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    void updateEntity(EstoqueRequest request, @org.mapstruct.MappingTarget Estoque estoque);

    @Named("produtoIdToProduto")
    default Produto produtoIdToProduto(Long produtoId) {
        if (produtoId == null) {
            return null;
        }
        Produto produto = new Produto();
        produto.setId(produtoId);
        return produto;
    }

    default Long calcularDiasParaVencimento(LocalDate dataValidade) {
        if (dataValidade == null) {
            return null;
        }
        LocalDate hoje = LocalDate.now();
        return java.time.temporal.ChronoUnit.DAYS.between(hoje, dataValidade);
    }
}