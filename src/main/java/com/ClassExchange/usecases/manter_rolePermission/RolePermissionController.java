package com.ClassExchange.usecases.manter_rolePermission;

import com.ClassExchange.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/role-permissoes")
@Tag(name = "RolePermission", description = "API para vínculo de roles a permissões")
public class RolePermissionController {

    private final RolePermissionService service;

    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Vincular role a permissão")
    public RolePermissionResponse criar(@Valid @RequestBody RolePermissionRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar vínculos role/permissão")
    public List<RolePermissionResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar vínculo por ID")
    public RolePermissionResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id).orElseThrow(() -> new NotFoundException("Vínculo não encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vínculo role/permissão")
    public RolePermissionResponse atualizar(@PathVariable UUID id, @Valid @RequestBody RolePermissionRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar vínculo role/permissão")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}

