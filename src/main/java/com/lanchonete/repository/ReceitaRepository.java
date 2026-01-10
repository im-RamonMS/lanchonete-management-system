package com.lanchonete.repository;

import com.lanchonete.model.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findByItemCardapioId(Long itemCardapioId);

    List<Receita> findByProdutoId(Long produtoId);

    Optional<Receita> findByItemCardapioIdAndProdutoId(Long itemCardapioId, Long produtoId);

    @Query("SELECT r FROM Receita r WHERE r.itemCardapio.id = :itemId AND r.ativo = true")
    List<Receita> findIngredientesAtivosByItemId(@Param("itemId") Long itemId);

    @Query("SELECT r FROM Receita r WHERE r.produto.id = :produtoId AND r.ativo = true")
    List<Receita> findItensQueUsamProduto(@Param("produtoId") Long produtoId);

    void deleteByItemCardapioId(Long itemCardapioId);

    boolean existsByItemCardapioIdAndProdutoId(Long itemCardapioId, Long produtoId);
}
