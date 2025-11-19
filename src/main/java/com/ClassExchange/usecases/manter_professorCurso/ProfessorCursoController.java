package com.ClassExchange.usecases.manter_professorCurso;

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
@RequestMapping("/api/professor-cursos")
@RequiredArgsConstructor
@Tag(name = "ProfessorCurso", description = "API para vínculo de usuários a cursos")
public class ProfessorCursoController {

    private final ProfessorCursoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar vínculo usuário/curso")
    public ProfessorCursoResponse criar(@Valid @RequestBody ProfessorCursoRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar vínculos usuário/curso")
    public List<ProfessorCursoResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar vínculo por ID")
    public ProfessorCursoResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new NotFoundException("ProfessorCurso não encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vínculo usuário/curso")
    public ProfessorCursoResponse atualizar(@PathVariable UUID id, @Valid @RequestBody ProfessorCursoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar vínculo usuário/curso")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}