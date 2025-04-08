package dev.senzalla.implementacao_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SwaggerController {

    @GetMapping("/")
    public RedirectView redirectToSwaggerUi() {
        return new RedirectView("/swagger-ui.html");
    }
    
    @GetMapping("/docs")
    public RedirectView redirectToDocs() {
        return new RedirectView("/swagger-ui.html");
    }
} 