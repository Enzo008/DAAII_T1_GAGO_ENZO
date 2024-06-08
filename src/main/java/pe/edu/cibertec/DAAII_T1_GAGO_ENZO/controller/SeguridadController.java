package pe.edu.cibertec.DAAII_T1_GAGO_ENZO.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAAII_T1_GAGO_ENZO.service.UsuarioService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/seguridad")
public class SeguridadController {

    public UsuarioService usuarioService;

    @GetMapping("/change-password")
    public String frmMantUsuario(Model model){
        return "seguridad/frmcambiarpassword";
    }

    @PostMapping("/change-password")
    public String cambiarContraseña(@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, HttpServletRequest request){
        if(password.equals(confirmPassword)){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            usuarioService.cambiarContrasena(username, password);

            // Invalidar la sesión y redirigir al usuario a la página de inicio de sesión
            request.getSession().invalidate();
            return "redirect:/auth/login";
        } else {
            // Las contraseñas no coinciden
            request.setAttribute("error", "Las contraseñas no coinciden");
            return "seguridad/frmcambiarpassword";
        }
    }





}
