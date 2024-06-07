package pe.edu.cibertec.DAAII_T1_GAGO_ENZO.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/home")
    public String home(HttpServletRequest request){
        return "home";
    }
}
