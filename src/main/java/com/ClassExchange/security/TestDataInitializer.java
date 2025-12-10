package com.ClassExchange.security;

import com.ClassExchange.domain.entity.Campus;
import com.ClassExchange.domain.entity.Usuario;
import com.ClassExchange.domain.enums.RoleUsuario;
import com.ClassExchange.usecases.manter_campus.CampusRepository;
import com.ClassExchange.usecases.manter_usuarios.UsuarioRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;

import java.util.Optional;

@Component
@Profile({"test","dev"})
public class TestDataInitializer implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final CampusRepository campusRepository;
    private final PasswordEncoder passwordEncoder;

    public TestDataInitializer(UsuarioRepository usuarioRepository, CampusRepository campusRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.campusRepository = campusRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(org.springframework.boot.ApplicationArguments args) {
        usuarioRepository.findAll().forEach(u -> {
            String s = u.getSenha();
            if (s != null && !s.startsWith("$2")) {
                u.setSenha(passwordEncoder.encode(s));
                usuarioRepository.save(u);
            }
        });

        Optional<Campus> campusOpt = campusRepository.findAll().stream().findFirst();
        Campus campus = campusOpt.orElse(null);

        createIfMissing("devprates@gmail.com", "teste123", "Dev Prates", RoleUsuario.ADMINISTRADOR, campus);
        createIfMissing("gabriel.bitencourt@estudante.ifms.edu.br", "teste123", "Gabriel Bitencourt", RoleUsuario.PROFESSOR, campus);
        createIfMissing("ga1134230@gmail.com", "teste123", "Usuário Coordenação", RoleUsuario.COORDENACAO, campus);
    }

    private void createIfMissing(String email, String senha, String nome, RoleUsuario role, Campus campus) {
        if (usuarioRepository.findByEmail(email).isEmpty()) {
            Usuario u = new Usuario();
            u.setNome(nome);
            u.setEmail(email);
            u.setSenha(passwordEncoder.encode(senha));
            u.setRole(role);
            u.setCelular("000000000");
            u.setCampus(campus);
            usuarioRepository.save(u);
        }
    }
}
