package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campus")
@RequiredArgsConstructor
@Tag(name = "Campus", description = "API para gerenciamento de campus")
public class CampusController {

    private final CampusService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo campus", description = "Cria um novo campus no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Campus criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Campus já existe")
    })
    public CampusResponse criar(@NonNull @Valid @RequestBody CampusRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar todos os campus", description = "Retorna uma lista com todos os campus cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de campus retornada com sucesso")
    public List<CampusResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar campus por ID", description = "Retorna um campus específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campus encontrado"),
            @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    })
    public CampusResponse buscarPorId(
            @Parameter(description = "ID do campus", required = true)
            @NonNull @PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "Buscar campus por slug", description = "Retorna um campus específico pelo seu slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campus encontrado"),
            @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    })
    public CampusResponse buscarPorSlug(
            @Parameter(description = "Slug do campus", required = true)
            @NonNull @PathVariable String slug) {
        return service.buscarPorSlug(slug)
                .orElseThrow(() -> new NotFoundException("Campus não encontrado"));
    }

    @GetMapping("/{id}/usuarios")
    @Operation(summary = "Listar usuários de um campus", description = "Retorna todos os usuários vinculados ao campus informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    })
    public java.util.List<com.ClassExchange.usecases.manter_campus.CampusResponse.UsuarioSimplificado> listarUsuariosDoCampus(
            @Parameter(description = "ID do campus", required = true)
            @NonNull @PathVariable java.util.UUID id) {
        return service.listarUsuariosDoCampus(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar campus", description = "Atualiza os dados de um campus existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Campus atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    })
    public CampusResponse atualizar(
            @Parameter(description = "ID do campus", required = true)
            @NonNull @PathVariable UUID id, 
            @NonNull @Valid @RequestBody CampusRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar campus", description = "Remove um campus do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Campus deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Campus não encontrado")
    })
    public void deletar(
            @Parameter(description = "ID do campus", required = true)
            @NonNull @PathVariable UUID id) {
        service.deletar(id);
    }
}
