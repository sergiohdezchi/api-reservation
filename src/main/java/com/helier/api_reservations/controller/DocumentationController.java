package com.helier.api_reservations.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;

@Hidden
@Controller
@RequestMapping("/api-docs")
public class DocumentationController {
    @ResponseBody
    @GetMapping
    public void redirectToSwaggerUi(HttpServletResponse response) {
        try {
            response.sendRedirect("swagger-ui/index.html");
        } catch (IOException e) {
            StringBuilder sb = new StringBuilder("UNEXPECTED ERROR: ");
            if (e.getMessage() != null) {
                sb.append(e.getMessage());
            }
        }
    }
}
