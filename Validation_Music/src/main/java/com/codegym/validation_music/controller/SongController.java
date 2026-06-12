package com.codegym.validation_music.controller;

import com.codegym.validation_music.model.Song;
import com.codegym.validation_music.service.SongService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping({"/", "/songs"})
    public String list(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "songs/list";
    }

    @GetMapping("/songs/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("formTitle", "Thêm mới bài hát");
        return "songs/form";
    }

    @PostMapping("/songs/create")
    public String create(@Valid @ModelAttribute("song") Song song,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formTitle", "Thêm mới bài hát");
            return "songs/form";
        }
        songService.save(song);
        redirectAttributes.addFlashAttribute("message", "Thêm mới bài hát thành công.");
        return "redirect:/songs";
    }

    @GetMapping("/songs/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Song song = songService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("song", song);
        model.addAttribute("formTitle", "Cập nhật bài hát");
        return "songs/form";
    }

    @PostMapping("/songs/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("song") Song song,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (songService.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        song.setId(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("formTitle", "Cập nhật bài hát");
            return "songs/form";
        }
        songService.save(song);
        redirectAttributes.addFlashAttribute("message", "Cập nhật bài hát thành công.");
        return "redirect:/songs";
    }
}
