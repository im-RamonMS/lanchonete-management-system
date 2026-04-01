package com.lanchonete.model.mapper;

import com.lanchonete.model.dto.request.ItemCardapioRequest;
import com.lanchonete.model.dto.response.ItemCardapioResponse;
import com.lanchonete.model.entity.ItemCardapio;
import com.lanchonete.model.entity.Receita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = {ReceitaMapper.class})
public interface ItemCardapioMapper {

    // Entity → Response
    @Mapping(target = "ingredientes", source = "ingredientes")
    @Mapping(target = "custoEstimado", expression = "java(calcularCustoEstimado(itemCardapio))")
    @Mapping(target = "margemLucro", expression = "java(calcularMargemLucro(itemCardapio))")
    ItemCardapioResponse toResponse(ItemCardapio itemCardapio);

    // Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredientes", ignore = true) // Será tratado separadamente
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "ativo", defaultValue = "true")
    @Mapping(target = "destaque", defaultValue = "false")
    ItemCardapio toEntity(ItemCardapioRequest request);

    // Update Entity from Request
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ingredientes", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    void updateEntity(ItemCardapioRequest request, @org.mapstruct.MappingTarget ItemCardapio itemCardapio);

    @Named("calcularCustoEstimado")
    default BigDecimal calcularCustoEstimado(ItemCardapio itemCardapio) {
        if (itemCardapio == null || itemCardapio.getIngredientes() == null) {
            return BigDecimal.ZERO;
        }
        return itemCardapio.getIngredientes().stream()
                .filter(Receita::getAtivo)
                .map(receita -> {
                    if (receita.getProduto() != null && receita.getProduto().getCustoUnitario() != null) {
                        return receita.getProduto().getCustoUnitario().multiply(receita.getQuantidade());
                    }
                    return BigDecimal.ZERO;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Named("calcularMargemLucro")
    default BigDecimal calcularMargemLucro(ItemCardapio itemCardapio) {
        BigDecimal custoEstimado = calcularCustoEstimado(itemCardapio);
        if (custoEstimado.equals(BigDecimal.ZERO) || itemCardapio.getPreco() == null) {
            return BigDecimal.ZERO;
        }
        return itemCardapio.getPreco().subtract(custoEstimado);
    }

    /* Comentei pq nao esta sendo usado
    // Metodo para simplificar ItemCardapio
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "preco", source = "preco")
    @Mapping(target = "categoria", source = "categoria")
    @Mapping(target = "ativo", source = "ativo")
    com.lanchonete.model.dto.ItemCardapioSimplesDTO toSimplesDTO(ItemCardapio itemCardapio);
    */
}