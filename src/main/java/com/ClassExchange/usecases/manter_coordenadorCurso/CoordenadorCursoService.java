package com.ClassExchange.usecases.manter_coordenadorCurso;

import com.ClassExchange.domain.entity.CoordenadorCurso;
import com.ClassExchange.domain.entity.Curso;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.enums.RoleUsuario;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.usecases.manter_cursos.CursoRepository;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ClassExchange.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CoordenadorCursoService {

    @Autowired
    private CoordenadorCursoRepository coordenadorCursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CoordenadorCursoMapper mapper;

    public CoordenadorCursoResponse criar(CoordenadorCursoRequest request) {
        java.util.Optional<CoordenadorCurso> existente = coordenadorCursoRepository.findByCursoIdAndFimIsNull(request.cursoId());
        if (existente.isPresent()) {
            throw new BusinessException("Curso já possui coordenador ativo");
        }
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + request.usuarioId()));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + request.cursoId()));

        CoordenadorCurso coordenadorCurso = CoordenadorCurso.builder()
                .inicio(request.inicio())
                .fim(request.fim())
                .usuario(usuario)
                .curso(curso)
                .build();

        usuario.setRole(RoleUsuario.COORDENADORCURSO);
        usuarioRepository.save(usuario);
        CoordenadorCurso coordenadorCursoSalvo = coordenadorCursoRepository.save(coordenadorCurso);
        return toResponse(coordenadorCursoSalvo);
    }

    @Transactional(readOnly = true)
    public List<CoordenadorCursoResponse> listarTodos() {
        return coordenadorCursoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<CoordenadorCursoResponse> buscarPorId(UUID id) {
        return coordenadorCursoRepository.findById(id)
                .map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public Optional<CoordenadorCursoResponse> buscarAtivoPorCurso(UUID cursoId) {
        if (!SecurityUtils.isAdmin()) {
            var cursoOpt = cursoRepository.findById(cursoId);
            if (cursoOpt.isEmpty()) return Optional.empty();
            var curso = cursoOpt.get();
            String currentCampus = SecurityUtils.currentCampusId();
            if (currentCampus == null || curso.getCampus() == null || !curso.getCampus().getId().toString().equals(currentCampus)) {
                return Optional.empty();
            }
        }
        return coordenadorCursoRepository.findByCursoIdAndFimIsNull(cursoId)
                .map(this::toResponse);
    }

    public CoordenadorCursoResponse atualizar(UUID id, CoordenadorCursoRequest request) {
        CoordenadorCurso coordenadorCurso = coordenadorCursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CoordenadorCurso não encontrado com ID: " + id));

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + request.usuarioId()));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + request.cursoId()));

        if (!curso.getId().equals(coordenadorCurso.getCurso().getId())) {
            java.util.Optional<CoordenadorCurso> existente = coordenadorCursoRepository.findByCursoIdAndFimIsNull(curso.getId());
            if (existente.isPresent() && !existente.get().getId().equals(coordenadorCurso.getId())) {
                throw new BusinessException("Curso já possui coordenador ativo");
            }
        }

        Usuario antigo = coordenadorCurso.getUsuario();
        if (antigo != null && !antigo.getId().equals(usuario.getId())) {
            if (antigo.getRole() == RoleUsuario.COORDENADORCURSO) {
                antigo.setRole(RoleUsuario.PROFESSOR);
                usuarioRepository.save(antigo);
            }
        }
        usuario.setRole(RoleUsuario.COORDENADORCURSO);
        usuarioRepository.save(usuario);

        coordenadorCurso.setInicio(request.inicio());
        coordenadorCurso.setFim(request.fim());
        coordenadorCurso.setUsuario(usuario);
        coordenadorCurso.setCurso(curso);

        CoordenadorCurso coordenadorCursoAtualizado = coordenadorCursoRepository.save(coordenadorCurso);
        return toResponse(coordenadorCursoAtualizado);
    }

    public void deletar(UUID id) {
        if (!coordenadorCursoRepository.existsById(id)) {
            throw new RuntimeException("CoordenadorCurso não encontrado com ID: " + id);
        }
        coordenadorCursoRepository.deleteById(id);
    }

    private CoordenadorCursoResponse toResponse(CoordenadorCurso coordenadorCurso) {
        return mapper.toResponse(coordenadorCurso);
    }
}
