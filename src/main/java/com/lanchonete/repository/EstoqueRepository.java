package com.lanchonete.repository;

import com.lanchonete.model.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProdutoId(Long produtoId);

    List<Estoque> findByAtivoTrue();

    @Query("SELECT e FROM Estoque e WHERE e.quantidade <= e.quantidadeMinima AND e.ativo = true")
    List<Estoque> findProdutosBaixoEstoque();

    @Query("SELECT e FROM Estoque e WHERE e.dataValidade BETWEEN :hoje AND :dataLimite AND e.ativo = true")
    List<Estoque> findProdutosProximoVencimento(@Param("hoje") LocalDate hoje,
                                                @Param("dataLimite") LocalDate dataLimite);

    List<Estoque> findByDataValidadeBefore(LocalDate data);

    @Query("SELECT e FROM Estoque e WHERE e.produto.categoria = :categoria AND e.ativo = true")
    List<Estoque> findByCategoriaProduto(@Param("categoria") String categoria);

    @Modifying
    @Transactional
    @Query("UPDATE Estoque e SET e.quantidade = e.quantidade + :quantidade WHERE e.id = :id")
    int aumentarQuantidade(@Param("id") Long id, @Param("quantidade") BigDecimal quantidade);

    @Modifying
    @Transactional
    @Query("UPDATE Estoque e SET e.quantidade = e.quantidade - :quantidade WHERE e.id = :id AND e.quantidade >= :quantidade")
    int diminuirQuantidade(@Param("id") Long id, @Param("quantidade") BigDecimal quantidade);
}
