package com.lanchonete.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
}