package com.ClassExchange.usecases.manter_estudanteDisciplina;

import com.ClassExchange.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/estudante-disciplinas")
@RequiredArgsConstructor
@Tag(name = "EstudanteDisciplina", description = "API para vínculo de estudantes às disciplinas/turmas")
public class EstudanteDisciplinaController {

    private final EstudanteDisciplinaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar vínculo estudante/disciplinaturma")
    public EstudanteDisciplinaResponse criar(@Valid @RequestBody EstudanteDisciplinaRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar vínculos estudante/disciplinaturma")
    public List<EstudanteDisciplinaResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar vínculo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vínculo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vínculo não encontrado")
    })
    public EstudanteDisciplinaResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("EstudanteDisciplina não encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vínculo estudante/disciplinaturma")
    public EstudanteDisciplinaResponse atualizar(@PathVariable UUID id, @Valid @RequestBody EstudanteDisciplinaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar vínculo estudante/disciplinaturma")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}