package com.ClassExchange.usecases.manter_aulas;

import com.ClassExchange.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/aulas")
@RequiredArgsConstructor
@Tag(name = "Aulas", description = "API para gerenciamento de aulas")
public class AulaController {

    private final AulaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar aula")
    public AulaResponse criar(@Valid @RequestBody AulaRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar aulas")
    public List<AulaResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aula por ID")
    public AulaResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id).orElseThrow(() -> new NotFoundException("Aula não encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aula")
    public AulaResponse atualizar(@PathVariable UUID id, @Valid @RequestBody AulaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar aula")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}

