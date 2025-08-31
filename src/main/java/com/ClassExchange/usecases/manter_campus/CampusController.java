package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/campus")
@RequiredArgsConstructor
public class CampusController {

    private final CampusService service;

    @PostMapping
    public CampusResponse criar(@Valid @RequestBody CampusRequest request) {
        if (request.nome().length() < 3) {
            throw new BusinessException("O nome do campus deve ter pelo menos 3 caracteres");
        }
        return service.criar(request);
    }

    @GetMapping
    public List<CampusResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public CampusResponse buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new com.ClassExchange.exception.NotFoundException("Campus não encontrado"));
    }

    @PutMapping("/{id}")
    public CampusResponse atualizar(@PathVariable UUID id, @Valid @RequestBody CampusRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
