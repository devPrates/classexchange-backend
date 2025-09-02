package com.ClassExchange.usecases.manter_locais;

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
@RequestMapping("/api/locais")
@RequiredArgsConstructor
@Tag(name = "Locais", description = "Operações relacionadas aos locais")
public class LocalController {

    private final LocalService service;

    @PostMapping
    @Operation(summary = "Criar um novo local", description = "Cria um novo local no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Local criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<LocalResponse> criar(@Valid @RequestBody LocalRequest request) {
        LocalResponse response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os locais", description = "Retorna uma lista com todos os locais cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de locais retornada com sucesso")
    public ResponseEntity<List<LocalResponse>> listarTodos() {
        List<LocalResponse> locais = service.listarTodos();
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar local por ID", description = "Retorna um local específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local encontrado"),
            @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    public ResponseEntity<LocalResponse> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar local", description = "Atualiza os dados de um local existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    public ResponseEntity<LocalResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody LocalRequest request) {
        LocalResponse response = service.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar local", description = "Remove um local do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Local deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Local não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}