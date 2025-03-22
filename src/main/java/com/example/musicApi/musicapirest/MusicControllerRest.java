package com.example.musicApi.musicapirest;

@RestController
@RequestMapping("api")
public class MusicControllerRest {
List<Music> musicList;

    @PostConstruct
    public void loadData() {
        musicList = new ArrayList<>();

        musicList.add(new Music( "Manager));
        musicList.add(new Music( "Artista"));

    }

    @GetMapping("/rock")
    public List<Student> listStudents() {
        return studentList;
    }

    @GetMapping("/clasica")
    public String hola() {
        return musicList;
    }