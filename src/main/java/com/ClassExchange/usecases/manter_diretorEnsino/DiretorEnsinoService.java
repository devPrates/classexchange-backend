package com.ClassExchange.usecases.manter_diretorEnsino;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.DiretorEnsino;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.enums.RoleUsuario;
import com.ClassExchange.exception.BusinessException;
import com.ClassExchange.usecases.manter_campus.CampusRepository;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import com.ClassExchange.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DiretorEnsinoService {

    @Autowired
    private DiretorEnsinoRepository diretorEnsinoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CampusRepository campusRepository;
    @Autowired
    private DiretorEnsinoMapper mapper;

    public DiretorEnsinoResponse criar(DiretorEnsinoRequest request) {
        Optional<DiretorEnsino> existente = diretorEnsinoRepository.findByCampusIdAndFimIsNull(request.campusId());
        if (existente.isPresent()) {
            throw new BusinessException("Campus já possui diretor de ensino ativo");
        }
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + request.usuarioId()));

        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new RuntimeException("Campus não encontrado com ID: " + request.campusId()));

        DiretorEnsino diretorEnsino = DiretorEnsino.builder()
                .inicio(request.inicio())
                .fim(request.fim())
                .usuario(usuario)
                .campus(campus)
                .build();

        usuario.setRole(RoleUsuario.DIRETORENSINO);
        usuarioRepository.save(usuario);
        DiretorEnsino diretorEnsinoSalvo = diretorEnsinoRepository.save(diretorEnsino);
        return toResponse(diretorEnsinoSalvo);
    }

    @Transactional(readOnly = true)
    public List<DiretorEnsinoResponse> listarTodos() {
        return diretorEnsinoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Optional<DiretorEnsinoResponse> buscarPorId(UUID id) {
        return diretorEnsinoRepository.findById(id)
                .map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public Optional<DiretorEnsinoResponse> buscarAtivoPorCampus(UUID campusId) {
        if (!SecurityUtils.isAdmin()) {
            String currentCampus = SecurityUtils.currentCampusId();
            if (currentCampus == null || !campusId.toString().equals(currentCampus)) {
                return Optional.empty();
            }
        }
        return diretorEnsinoRepository.findByCampusIdAndFimIsNull(campusId)
                .map(this::toResponse);
    }

    public DiretorEnsinoResponse atualizar(UUID id, DiretorEnsinoRequest request) {
        DiretorEnsino diretorEnsino = diretorEnsinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DiretorEnsino não encontrado com ID: " + id));
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + request.usuarioId()));

        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new RuntimeException("Campus não encontrado com ID: " + request.campusId()));

        if (!campus.getId().equals(diretorEnsino.getCampus().getId())) {
            Optional<DiretorEnsino> existente = diretorEnsinoRepository.findByCampusIdAndFimIsNull(campus.getId());
            if (existente.isPresent() && !existente.get().getId().equals(diretorEnsino.getId())) {
                throw new BusinessException("Campus já possui diretor de ensino ativo");
            }
        }

        Usuario antigo = diretorEnsino.getUsuario();
        if (antigo != null && !antigo.getId().equals(usuario.getId())) {
            if (antigo.getRole() == RoleUsuario.DIRETORENSINO) {
                antigo.setRole(RoleUsuario.PROFESSOR);
                usuarioRepository.save(antigo);
            }
        }
        usuario.setRole(RoleUsuario.DIRETORENSINO);
        usuarioRepository.save(usuario);

        diretorEnsino.setInicio(request.inicio());
        diretorEnsino.setFim(request.fim());
        diretorEnsino.setUsuario(usuario);
        diretorEnsino.setCampus(campus);

        DiretorEnsino diretorEnsinoAtualizado = diretorEnsinoRepository.save(diretorEnsino);
        return toResponse(diretorEnsinoAtualizado);
    }

    public void deletar(UUID id) {
        if (!diretorEnsinoRepository.existsById(id)) {
            throw new RuntimeException("DiretorEnsino não encontrado com ID: " + id);
        }
        diretorEnsinoRepository.deleteById(id);
    }

    private DiretorEnsinoResponse toResponse(DiretorEnsino diretorEnsino) {
        return mapper.toResponse(diretorEnsino);
    }
}
