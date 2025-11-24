package com.ClassExchange.usecases.manter_usuarioRole;

import com.ClassExchange.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario-roles")
@Tag(name = "UsuarioRole", description = "API para vínculo de usuários a roles")
public class UsuarioRoleController {

    private final UsuarioRoleService service;

    public UsuarioRoleController(UsuarioRoleService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Vincular usuário a role")
    public UsuarioRoleResponse criar(@Valid @RequestBody UsuarioRoleRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar vínculos usuário/role")
    public List<UsuarioRoleResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar vínculo por ID")
    public UsuarioRoleResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id).orElseThrow(() -> new NotFoundException("Vínculo não encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vínculo usuário/role")
    public UsuarioRoleResponse atualizar(@PathVariable UUID id, @Valid @RequestBody UsuarioRoleRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar vínculo usuário/role")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}

