package com.example.musicApi.musicapirest;

import com.example.musicApi.entity.Music;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/music")
public class MusicControllerRest {

    private List<Music> musicList;

    @PostConstruct
    public void loadData() {
        musicList = new ArrayList<>();
        musicList.add(new Music("Despacito", "Luis Fonsi"));
        musicList.add(new Music("Beat It", "Michael Jackson"));
        musicList.add(new Music("pump it ", "Black eyed peas"));
    }

    @GetMapping
    public List<Music> showCatalog() {
        return musicList;
    }

    @GetMapping("/title/{title}")
    public Music getMusicByName(@PathVariable String title) {
        Optional<Music> song = musicList.stream()
                .filter(music -> music.getTitle().equalsIgnoreCase(title))
                .findFirst();

        return song.orElse(null);
    }

    @GetMapping("/artist/{artist}")
    public Music getMusicByArtist(@PathVariable String artist) {
        Optional<Music> song = musicList.stream()
                .filter(music -> music.getArtist().equalsIgnoreCase(artist))
                .findFirst();

        return song.orElse(null);
    }

    @PostMapping
    public String addMusic(@RequestBody Music newMusic) {
        Optional<Music> existingMusic = musicList.stream()
                .filter(music -> music.getTitle().equalsIgnoreCase(newMusic.getTitle()))
                .findFirst();

        if (existingMusic.isPresent()) {
            return "La canción ya existe!";
        }

        musicList.add(newMusic);
        return "Canción agregada exitosamente!";
    }

    // Nuevo método para manejar PUT en la ruta base
    @PutMapping
    public String updateMusic(@RequestBody Music updatedMusic) {
        Optional<Music> existingMusic = musicList.stream()
                .filter(music -> music.getTitle().equalsIgnoreCase(updatedMusic.getTitle()))
                .findFirst();

        if (existingMusic.isPresent()) {
            Music music = existingMusic.get();
            music.setTitle(updatedMusic.getTitle());
            music.setArtist(updatedMusic.getArtist());
            return "Canción actualizada con éxito!";
        }

        return "Canción no encontrada!";
    }

    @PutMapping("/title/{title}")
    public String updateMusic(@PathVariable String title, @RequestBody Music updatedMusic) {
        for (Music music : musicList) {
            if (music.getTitle().equalsIgnoreCase(title)) {
                music.setTitle(updatedMusic.getTitle());
                music.setArtist(updatedMusic.getArtist());
                return "Canción actualizada con éxito!";
            }
        }
        return "Canción no encontrada!";
    }

    @PutMapping("/artist/{artist}")
    public String updateArtist(@PathVariable String artist, @RequestBody Music updatedMusic) {
        for (Music music : musicList) {
            if (music.getArtist().equalsIgnoreCase(artist)) {
                music.setTitle(updatedMusic.getTitle());
                music.setArtist(updatedMusic.getArtist());
                return "Artista actualizado con éxito!";
            }
        }
        return "Artista no encontrado!";
    }

    @DeleteMapping("/title/{title}")
    public String deleteMusic(@PathVariable String title) {
        boolean removed = musicList.removeIf(music -> music.getTitle().equalsIgnoreCase(title));
        return removed ? "Canción eliminada!" : "Canción no encontrada!";
    }
}