package pe.edu.cibertec.DAAII_T1_GAGO_ENZO.service;

import pe.edu.cibertec.DAAII_T1_GAGO_ENZO.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario buscarUsuarioXNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    Usuario cambiarContrasena(String username, String newPassword);
}