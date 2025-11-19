package com.ClassExchange.usecases.manter_disciplinaTurma;

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
@RequestMapping("/api/disciplina-turmas")
@RequiredArgsConstructor
@Tag(name = "DisciplinaTurma", description = "API para vincular disciplinas a turmas e locais")
public class DisciplinaTurmaController {

    private final DisciplinaTurmaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar vínculo disciplina/turma/local")
    public DisciplinaTurmaResponse criar(@Valid @RequestBody DisciplinaTurmaRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar vínculos disciplina/turma/local")
    public List<DisciplinaTurmaResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar vínculo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vínculo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vínculo não encontrado")
    })
    public DisciplinaTurmaResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("DisciplinaTurma não encontrada"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vínculo disciplina/turma/local")
    public DisciplinaTurmaResponse atualizar(@PathVariable UUID id, @Valid @RequestBody DisciplinaTurmaRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar vínculo disciplina/turma/local")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}