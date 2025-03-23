package com.example.musicApi.musicapirest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/music")
public class MusicControllerRest {

    private static final String CATALOG_MESSAGE = "Catálogo de música disponible para todos los usuarios.";
    private static final String PREMIUM_MESSAGE = "Contenido premium solo para usuarios con rol ADMIN.";

    @GetMapping("/catalog")
    public String showCatalog() {
        return CATALOG_MESSAGE;
    }

    @GetMapping("/premium")
    public String accessPremium() {
        return PREMIUM_MESSAGE;
    }
}
