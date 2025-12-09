package com.ClassExchange.usecases.manter_coordenadorCurso;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/coordenadorCurso")
@Tag(name = "Coordenador Curso", description = "API para gerenciamento de coordenadores de curso")
public class CoordenadorCursoController {

    @Autowired
    private CoordenadorCursoService coordenadorCursoService;

    @PostMapping
    @Operation(summary = "Criar uma nova coordenação de curso", description = "Cria uma nova associação entre professor e curso como coordenador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Coordenação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CoordenadorCursoResponse> criar(@Valid @RequestBody CoordenadorCursoRequest request) {
        CoordenadorCursoResponse response = coordenadorCursoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todas as coordenações de curso", description = "Retorna uma lista com todas as coordenações cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de coordenações retornada com sucesso")
    public ResponseEntity<List<CoordenadorCursoResponse>> listarTodos() {
        List<CoordenadorCursoResponse> coordenacoes = coordenadorCursoService.listarTodos();
        return ResponseEntity.ok(coordenacoes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar coordenação por ID", description = "Retorna uma coordenação específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coordenação encontrada"),
            @ApiResponse(responseCode = "404", description = "Coordenação não encontrada")
    })
    public ResponseEntity<CoordenadorCursoResponse> buscarPorId(@PathVariable UUID id) {
        Optional<CoordenadorCursoResponse> coordenacao = coordenadorCursoService.buscarPorId(id);
        return coordenacao.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/curso/{cursoId}")
    @Operation(summary = "Coordenador de curso ativo por curso", description = "Retorna o coordenador de curso ativo para o curso informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coordenador retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Coordenador não encontrado para o curso")
    })
    public ResponseEntity<CoordenadorCursoResponse> buscarAtivoPorCurso(@PathVariable UUID cursoId) {
        Optional<CoordenadorCursoResponse> coord = coordenadorCursoService.buscarAtivoPorCurso(cursoId);
        return coord.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar coordenação de curso", description = "Atualiza os dados de uma coordenação existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coordenação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Coordenação não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CoordenadorCursoResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody CoordenadorCursoRequest request) {
        try {
            CoordenadorCursoResponse response = coordenadorCursoService.atualizar(id, request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar coordenação de curso", description = "Remove uma coordenação do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Coordenação deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Coordenação não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        try {
            coordenadorCursoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
