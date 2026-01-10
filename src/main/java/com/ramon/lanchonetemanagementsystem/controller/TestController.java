package com.ramon.lanchonetemanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Teste", description = "Endpoints para teste da aplicação")
public class TestController {

    @GetMapping("/hello")
    @Operation(
            summary = "Hello World",
            description = "Endpoint de teste básico para verificar se a API está funcionando"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public String hello() {
        return "Sistema de Gestão para Lanchonetes está funcionando! 🚀";
    }

    @GetMapping("/status")
    @Operation(summary = "Status da aplicação", description = "Verifica o status da aplicação")
    public String status() {
        return """
               {
                 "status": "online",
                 "service": "lanchonete-management-system",
                 "version": "1.0.0",
                 "timestamp": "%s"
               }
               """.formatted(java.time.LocalDateTime.now());
    }
}