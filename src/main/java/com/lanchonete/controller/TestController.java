package com.lanchonete.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Teste", description = "Endpoints para teste da aplicação")
public class TestController {

    @Autowired
    private com.lanchonete.repository.ProdutoRepository produtoRepository;

    @GetMapping("/hello")
    @Operation(summary = "Hello World")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Sistema de Gestão para Lanchonetes está funcionando! 🚀");
    }

    @GetMapping("/status")
    @Operation(summary = "Status da aplicação")
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> status = Map.of(
                "status", "online",
                "service", "lanchonete-management-system",
                "version", "1.0.0",
                "timestamp", LocalDateTime.now().toString()
        );
        return ResponseEntity.ok(status);
    }

    @GetMapping("/db-check")
    @Operation(summary = "Verificar conexão com o banco de dados", description = "Testa se o banco de dados está conectado executando uma consulta simples")
    public ResponseEntity<String> checkDatabaseConnection() {
        try {
            long count = produtoRepository.count();
            return ResponseEntity.ok("Banco de dados conectado! Total de produtos: " + count);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro na conexão com o banco de dados: " + e.getMessage());
        }
    }
}