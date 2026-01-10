package com.lanchonete.repository;

import com.lanchonete.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByAtivoTrue();

    List<Produto> findByCategoria(String categoria);

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT p FROM Produto p WHERE p.ativo = true AND p.custoUnitario <= :maxCusto")
    List<Produto> findProdutosBaratos(@Param("maxCusto") Double maxCusto);

    // CORREÇÃO: findByNome em vez de findById
    Optional<Produto> findByNome(String nome);

    boolean existsByNome(String nome);
}