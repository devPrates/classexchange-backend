package com.ClassExchange.usecases.manter_cargaHoraria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cargas-horarias")
@RequiredArgsConstructor
@Tag(name = "Cargas Horárias", description = "Operações relacionadas às cargas horárias")
public class CargaHorariaController {

    private final CargaHorariaService service;

    @PostMapping
    @Operation(summary = "Criar uma nova carga horária", description = "Cria uma nova carga horária no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carga horária criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<CargaHorariaResponse> criar(@Valid @RequestBody CargaHorariaRequest request) {
        CargaHorariaResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas as cargas horárias", description = "Retorna uma lista com todas as cargas horárias cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de cargas horárias retornada com sucesso")
    public ResponseEntity<List<CargaHorariaResponse>> listarTodos() {
        List<CargaHorariaResponse> cargasHorarias = service.listarTodos();
        return ResponseEntity.ok(cargasHorarias);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar carga horária por ID", description = "Retorna uma carga horária específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carga horária encontrada"),
            @ApiResponse(responseCode = "404", description = "Carga horária não encontrada")
    })
    public ResponseEntity<CargaHorariaResponse> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar carga horária", description = "Atualiza os dados de uma carga horária existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carga horária atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Carga horária não encontrada")
    })
    public ResponseEntity<CargaHorariaResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody CargaHorariaRequest request) {
        CargaHorariaResponse response = service.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar carga horária", description = "Remove uma carga horária do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carga horária deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carga horária não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}