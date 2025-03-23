package com.example.musicApi.musicapirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/music")
public class MusicControllerRest {

    @GetMapping("/catalog")
    public String showCatalog() {
        return "Catálogo de música disponible para todos los usuarios.";
    }

    @GetMapping("/premium")
    public String accessPremium() {
        return "Contenido premium solo para usuarios con rol ADMIN.";
    }
}