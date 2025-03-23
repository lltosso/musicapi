package com.example.musicApi.musicapirest;

import com.example.musicApi.entity.Music;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MusicControllerRest {

    private List<Music> musicList;

    @PostConstruct
    public void loadData() {
        musicList = new ArrayList<>();
        musicList.add(new Music("Despacito", "Luis Fonsi"));
        musicList.add(new Music("Beat It", "Michael Jackson"));
        musicList.add(new Music("pump it ", "Black eyed peas"));
    }

    @GetMapping("/music")
    public List<Music> showCatalog() {
        return musicList;
    }



    @GetMapping("/{title}")
    public Music getMusicByName(@PathVariable String title) {
        Optional<Music> song = musicList.stream()
                .filter(music -> music.getTitle().equalsIgnoreCase(title))
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



    @PutMapping("/{title}")
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



    @DeleteMapping("/{title}")
    public String deleteMusic(@PathVariable String title) {
        boolean removed = musicList.removeIf(music -> music.getTitle().equalsIgnoreCase(title));
        return removed ? "Canción eliminada!" : "Canción no encontrada!";
    }

}