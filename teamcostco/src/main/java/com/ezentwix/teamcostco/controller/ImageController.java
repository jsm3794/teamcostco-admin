package com.ezentwix.teamcostco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import java.io.IOException;

@Controller
public class ImageController {

    @GetMapping("/images/logo.png")
    public void getLogo(HttpServletRequest request, HttpServletResponse response) {
        String theme = "light"; // Default theme

        // Check for the 'theme' cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("theme".equals(cookie.getName())) {
                    theme = cookie.getValue();
                    break;
                }
            }
        }

        // Determine the image to serve
        String imagePath = theme.equals("dark") ? "/images/logo-dark.png" : "/images/logo-light.png";

        // Forward the request to the correct image
        try {
            request.getRequestDispatcher(imagePath).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
