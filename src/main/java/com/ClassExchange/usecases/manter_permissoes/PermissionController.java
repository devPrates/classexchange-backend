package com.ClassExchange.usecases.manter_permissoes;

import com.ClassExchange.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/permissoes")
@Tag(name = "Permissões", description = "API para gerenciamento de permissões")
public class PermissionController {

    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar permissão")
    public PermissionResponse criar(@Valid @RequestBody PermissionRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar permissões")
    public List<PermissionResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar permissão por ID")
    public PermissionResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id).orElseThrow(() -> new NotFoundException("Permissão não encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar permissão")
    public PermissionResponse atualizar(@PathVariable UUID id, @Valid @RequestBody PermissionRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar permissão")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}

