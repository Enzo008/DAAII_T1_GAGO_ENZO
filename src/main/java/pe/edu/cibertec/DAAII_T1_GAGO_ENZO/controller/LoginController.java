package pe.edu.cibertec.DAAII_T1_GAGO_ENZO.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.DAAII_T1_GAGO_ENZO.model.bd.Usuario;
import pe.edu.cibertec.DAAII_T1_GAGO_ENZO.service.UsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(HttpServletRequest request){
        return "auth/frmLogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Usuario usuario = usuarioService.buscarUsuarioXNomUsuario(username);
        request.getSession().setAttribute("nombre", usuario.getNomusuario());
        return "redirect:/auth/dashboard";
    }


    @GetMapping("/dashboard")
    public String dashboard(){
        return "auth/home";
    }

    @GetMapping("/register")
    public String Registro(HttpServletRequest request){
        return "auth/frmRegistro";
    }

    @PostMapping("/save-user")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "auth/frmLogin";
    }
}
