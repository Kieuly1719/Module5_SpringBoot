package com.codegym.validation_music.service;

import com.codegym.validation_music.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<Song> findAll();

    Optional<Song> findById(Long id);

    Song save(Song song);
}
