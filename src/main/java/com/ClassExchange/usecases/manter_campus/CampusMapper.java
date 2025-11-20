package com.ClassExchange.usecases.manter_campus;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Usuario;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampusMapper {
    @NonNull
    public CampusResponse toResponse(Campus campus) {
        return new CampusResponse(
                campus.getId(),
                campus.getNome(),
                campus.getSigla(),
                campus.getEmail(),
                campus.getSlug(),
                campus.getTelefone(),
                campus.getEndereco(),
                null,
                null,
                0L,
                campus.getCreatedAt(),
                campus.getUpdatedAt()
        );
    }

    @NonNull
    public CampusResponse toResponseWithCursosAndDiretor(Campus campus, List<CampusResponse.CursoSimplificado> cursos, CampusResponse.DiretorEnsinoSimplificado diretorDto, long usuariosCount) {
        return new CampusResponse(
                campus.getId(),
                campus.getNome(),
                campus.getSigla(),
                campus.getEmail(),
                campus.getSlug(),
                campus.getTelefone(),
                campus.getEndereco(),
                diretorDto,
                cursos,
                usuariosCount,
                campus.getCreatedAt(),
                campus.getUpdatedAt()
        );
    }

    @NonNull
    public CampusResponse.CursoSimplificado toCursoSimplificado(Curso curso) {
        return new CampusResponse.CursoSimplificado(
                curso.getId(),
                curso.getNome(),
                curso.getSigla(),
                null
        );
    }

    @NonNull
    public CampusResponse.CoordenadorCursoSimplificado toCoordenadorSimplificado(com.ClassExchange.domain.entity.CoordenadorCurso cc) {
        return new CampusResponse.CoordenadorCursoSimplificado(
                cc.getId(),
                cc.getUsuario() != null ? cc.getUsuario().getId() : null,
                cc.getUsuario() != null ? cc.getUsuario().getNome() : null,
                cc.getUsuario() != null ? cc.getUsuario().getEmail() : null
        );
    }

    @NonNull
    public CampusResponse.DiretorEnsinoSimplificado toDiretorSimplificado(com.ClassExchange.domain.entity.DiretorEnsino de) {
        return new CampusResponse.DiretorEnsinoSimplificado(
                de.getId(),
                de.getUsuario() != null ? de.getUsuario().getId() : null,
                de.getUsuario() != null ? de.getUsuario().getNome() : null,
                de.getUsuario() != null ? de.getUsuario().getEmail() : null
        );
    }

    public List<CampusResponse.CursoSimplificado> toCursoSimplificadoList(List<Curso> cursos) {
        return cursos.stream().map(this::toCursoSimplificado).toList();
    }

    @NonNull
    public CampusResponse.UsuarioSimplificado toUsuarioSimplificado(Usuario usuario) {
        return new CampusResponse.UsuarioSimplificado(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCelular(),
                usuario.getRole()
        );
    }

    @NonNull
    public Campus toEntity(CampusRequest request) {
        Campus campus = new Campus();
        campus.setNome(request.nome());
        campus.setSigla(request.sigla());
        campus.setEmail(request.email());
        campus.setTelefone(request.telefone());
        campus.setEndereco(request.endereco());
        campus.setSlug(com.ClassExchange.utils.SlugUtils.generateSlug(request.nome()));
        return campus;
    }

    public void updateEntityFromRequest(CampusRequest request, Campus campus) {
        campus.setNome(request.nome());
        campus.setSigla(request.sigla());
        campus.setEmail(request.email());
        campus.setTelefone(request.telefone());
        campus.setEndereco(request.endereco());
        campus.setSlug(com.ClassExchange.utils.SlugUtils.generateSlug(request.nome()));
    }
}