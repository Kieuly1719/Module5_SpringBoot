package com.codegym.validation_music;

import com.codegym.validation_music.model.Song;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SongValidationTests {

    @Autowired
    private Validator validator;

    @Test
    void acceptsValidVietnameseSongInformationAndCommaInGenre() {
        Song song = new Song(null, "Nối Vòng Tay Lớn", "Trịnh Công Sơn", "Nhạc Trịnh, Dân Ca");

        assertThat(validator.validate(song)).isEmpty();
    }

    @Test
    void rejectsBlankFields() {
        Song song = new Song(null, " ", "", " ");

        Set<ConstraintViolation<Song>> violations = validator.validate(song);

        assertThat(violations).extracting(violation -> violation.getPropertyPath().toString())
                .contains("name", "artist", "genre");
    }

    @Test
    void rejectsSpecialCharacters() {
        Song song = new Song(null, "Tên bài hát @", "Nghệ sĩ +", "Pop; Rock");

        Set<ConstraintViolation<Song>> violations = validator.validate(song);

        assertThat(violations).extracting(violation -> violation.getPropertyPath().toString())
                .containsExactlyInAnyOrder("name", "artist", "genre");
    }

    @Test
    void rejectsValuesOverMaximumLength() {
        Song song = new Song(null, "a".repeat(801), "b".repeat(301), "c".repeat(1001));

        Set<ConstraintViolation<Song>> violations = validator.validate(song);

        assertThat(violations).extracting(violation -> violation.getPropertyPath().toString())
                .containsExactlyInAnyOrder("name", "artist", "genre");
    }
}
