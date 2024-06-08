package pe.edu.cibertec.DAAII_T1_GAGO_ENZO.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAAII_T1_GAGO_ENZO.model.bd.Rol;
import pe.edu.cibertec.DAAII_T1_GAGO_ENZO.model.bd.Usuario;
import pe.edu.cibertec.DAAII_T1_GAGO_ENZO.repository.RolRepository;
import pe.edu.cibertec.DAAII_T1_GAGO_ENZO.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService implements IUsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(
                usuario.getPassword()));
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));

        return usuarioRepository.save(usuario);
    }
    @Override
    public Usuario cambiarContrasena(String username, String newPassword) {
        Usuario usuario = buscarUsuarioXNomUsuario(username);
        usuario.setPassword(passwordEncoder.encode(newPassword));
        return guardarUsuario(usuario);
    }


}