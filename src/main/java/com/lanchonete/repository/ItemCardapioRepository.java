package com.lanchonete.repository;

import com.lanchonete.model.entity.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {
    List<ItemCardapio> findByAtivoTrue();

    List<ItemCardapio> findByCategoria(String categoria);

    List<ItemCardapio> findByDestaqueTrueAndAtivoTrue();

    List<ItemCardapio> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    @Query("SELECT i FROM ItemCardapio i WHERE i.ativo = true AND LOWER(i.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<ItemCardapio> searchByNome(@Param("nome") String nome);

    boolean existsByNome(String nome);
}
