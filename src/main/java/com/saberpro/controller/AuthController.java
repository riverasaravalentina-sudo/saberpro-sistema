package com.saberpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    /**
     * Página de login
     */
    @GetMapping("/login")
    public String login(Model model, 
                       @RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout) {
        
        if (error != null) {
            model.addAttribute("errorMsg", "Credenciales inválidas. Por favor, intente de nuevo.");
        }
        
        if (logout != null) {
            model.addAttribute("logoutMsg", "Sesión cerrada correctamente.");
        }
        
        return "auth/login";
    }
}