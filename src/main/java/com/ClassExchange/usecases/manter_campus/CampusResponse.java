package com.ClassExchange.usecases.manter_campus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CampusResponse(
        UUID id,
        String nome,
        String sigla,
        String email,
        String slug,
        String telefone,
        String endereco,
        DiretorEnsinoSimplificado diretorEnsino,
        List<CursoSimplificado> cursos,
        long usuariosCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record CursoSimplificado(
            UUID id,
            String nome,
            String sigla,
            CoordenadorCursoSimplificado coordenador
    ) {}

    public record DiretorEnsinoSimplificado(
            UUID id,
            UUID usuarioId,
            String usuarioNome,
            String usuarioEmail
    ) {}

    public record CoordenadorCursoSimplificado(
            UUID id,
            UUID usuarioId,
            String usuarioNome,
            String usuarioEmail
    ) {}

    public record UsuarioSimplificado(
            UUID id,
            String nome,
            String email,
            String celular,
            com.ClassExchange.domain.enums.RoleUsuario role
    ) {}
}