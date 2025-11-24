package com.ClassExchange.usecases.manter_roles;

import com.ClassExchange.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "API para gerenciamento de roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar role")
    public RoleResponse criar(@Valid @RequestBody RoleRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar roles")
    public List<RoleResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar role por ID")
    public RoleResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id).orElseThrow(() -> new NotFoundException("Role não encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar role")
    public RoleResponse atualizar(@PathVariable UUID id, @Valid @RequestBody RoleRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar role")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
