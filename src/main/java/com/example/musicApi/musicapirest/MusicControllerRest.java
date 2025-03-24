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
        musicList.add(new Music("1", "Despacito", "Luis Fonsi"));
        musicList.add(new Music("2", "Beat It", "Michael Jackson"));
        musicList.add(new Music("3", "Pump It", "Black Eyed Peas"));
    }


    @GetMapping
    public List<Music> showCatalog() {
        return musicList;
    }


    @GetMapping("/{id}")
    public String getMusicById(@PathVariable String id) {
        Optional<Music> song = musicList.stream()
                .filter(music -> music.getId().equals(id))
                .findFirst();

        if (song.isPresent()) {
            Music music = song.get();
            return "ID: " + music.getId() + ", Title: " + music.getTitle() + ", Artist: " + music.getArtist();
        } else {
            return "Error: Canción no encontrada!";
        }
    }


    @PostMapping
    public String addMusic(@RequestBody Music newMusic) {

        Optional<Music> existingMusic = musicList.stream()
                .filter(music -> music.getId().equals(newMusic.getId()))
                .findFirst();

        if (existingMusic.isPresent()) {
            return "Error: Ya existe una canción con este ID!";
        }


        musicList.add(newMusic);


        return "Canción agregada exitosamente! ID: " + newMusic.getId() + ", Title: " + newMusic.getTitle() + ", Artist: " + newMusic.getArtist();
    }

    @PutMapping("/{id}")
    public String updateMusic(@PathVariable String id, @RequestBody Music updatedMusic) {
        Optional<Music> existingMusic = musicList.stream()
                .filter(music -> music.getId().equals(id))
                .findFirst();

        if (existingMusic.isPresent()) {
            Music music = existingMusic.get();
            music.setTitle(updatedMusic.getTitle());
            music.setArtist(updatedMusic.getArtist());
            return "Canción actualizada con éxito! ID: " + music.getId() + ", Title: " + music.getTitle() + ", Artist: " + music.getArtist();
        }

        return "Error: Canción no encontrada!";
    }


    @DeleteMapping("/{id}")
    public String deleteMusic(@PathVariable String id) {
        boolean removed = musicList.removeIf(music -> music.getId().equals(id));
        return removed ? "Canción eliminada!" : "Error: Canción no encontrada!";
    }
}