package com.ClassExchange.usecases.manter_estudanteCurso;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudante-cursos")
@RequiredArgsConstructor
@Tag(name = "EstudanteCurso", description = "API para vínculo de estudantes aos cursos")
public class EstudanteCursoController {

    private final EstudanteCursoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Vincular estudante ao curso")
    @ApiResponse(responseCode = "201", description = "Vínculo criado com sucesso")
    public EstudanteCursoResponse criar(@Valid @RequestBody EstudanteCursoRequest request) {
        return service.criar(request);
    }

    @GetMapping
    @Operation(summary = "Listar vínculos estudante/curso")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public java.util.List<EstudanteCursoResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar vínculo por ID")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vínculo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vínculo não encontrado")
    })
    public EstudanteCursoResponse buscarPorId(@org.springframework.web.bind.annotation.PathVariable java.util.UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new com.ClassExchange.exception.NotFoundException("EstudanteCurso não encontrado"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vínculo estudante/curso")
    @ApiResponse(responseCode = "200", description = "Vínculo atualizado com sucesso")
    public EstudanteCursoResponse atualizar(@org.springframework.web.bind.annotation.PathVariable java.util.UUID id,
                                            @Valid @RequestBody EstudanteCursoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar vínculo estudante/curso")
    @ApiResponse(responseCode = "204", description = "Vínculo deletado com sucesso")
    public void deletar(@org.springframework.web.bind.annotation.PathVariable java.util.UUID id) {
        service.deletar(id);
    }
}