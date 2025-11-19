package com.ClassExchange.usecases.manter_horarios;

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
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
@Tag(name = "Horários", description = "API para gerenciamento de horários de disciplinas/turmas")
public class HorarioController {

    private final HorarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar horário")
    public HorarioResponse criar(@Valid @RequestBody HorarioRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar horários")
    public List<HorarioResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar horário por ID")
    public HorarioResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("Horario não encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar horário")
    public HorarioResponse atualizar(@PathVariable UUID id, @Valid @RequestBody HorarioRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar horário")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}