package com.codegym.validation_music.repository;

import com.codegym.validation_music.model.Song;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemorySongRepository implements SongRepository {

    private final Map<Long, Song> songs = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong();

    public InMemorySongRepository() {
        save(new Song(null, "Nối Vòng Tay Lớn", "Trịnh Công Sơn", "Nhạc Trịnh"));
        save(new Song(null, "Một Nhà", "Da LAB", "Pop, Rap"));
    }

    @Override
    public List<Song> findAll() {
        return songs.values().stream()
                .sorted(Comparator.comparing(Song::getId))
                .toList();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return Optional.ofNullable(songs.get(id));
    }

    @Override
    public Song save(Song song) {
        if (song.getId() == null) {
            song.setId(currentId.incrementAndGet());
        }
        songs.put(song.getId(), song);
        return song;
    }
}
