package com.lanchonete.model.mapper;

import com.lanchonete.model.dto.request.ReceitaRequest;
import com.lanchonete.model.dto.response.ReceitaResponse;
import com.lanchonete.model.entity.Receita;
import com.lanchonete.model.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {

    // Entity → Response
    @Mapping(source = "produto.id", target = "produtoId")
    @Mapping(source = "produto.nome", target = "produtoNome")
    @Mapping(source = "produto.unidadeMedida", target = "produtoUnidadeMedida")
    @Mapping(target = "custoIngrediente", expression = "java(calcularCustoIngrediente(receita))")
    ReceitaResponse toResponse(Receita receita);

    // Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemCardapio", ignore = true) // Será setado no service
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoIdToProduto")
    @Mapping(target = "ativo", defaultValue = "true")
    Receita toEntity(ReceitaRequest request);

    // Update Entity from Request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemCardapio", ignore = true)
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoIdToProduto")
    void updateEntity(ReceitaRequest request, @org.mapstruct.MappingTarget Receita receita);

    @Named("produtoIdToProduto")
    default Produto produtoIdToProduto(Long produtoId) {
        if (produtoId == null) {
            return null;
        }
        Produto produto = new Produto();
        produto.setId(produtoId);
        return produto;
    }

    @Named("calcularCustoIngrediente")
    default BigDecimal calcularCustoIngrediente(Receita receita) {
        if (receita == null || receita.getProduto() == null ||
                receita.getProduto().getCustoUnitario() == null ||
                receita.getQuantidade() == null) {
            return BigDecimal.ZERO;
        }
        return receita.getProduto().getCustoUnitario().multiply(receita.getQuantidade());
    }
}