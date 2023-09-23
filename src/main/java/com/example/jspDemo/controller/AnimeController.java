package com.example.jspDemo.controller;

import com.example.jspDemo.modal.Anime;
import com.example.jspDemo.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AnimeController {

    @Autowired
    AnimeService animeService;

    @GetMapping({"/", "/viewAnimeList"})
    public String viewAnimeList(@ModelAttribute("message") String message, Model model){
        model.addAttribute("animeList", animeService.getAllAnime());
        model.addAttribute("message", message);

        return "ViewAnimeList";
    }

    @GetMapping("/addAnime")
    public String addAnime(@ModelAttribute("message") String message,Model model){
        model.addAttribute("anime", new Anime());
        model.addAttribute("message", message);

        return "AddAnime";
    }

    @PostMapping("/saveAnime")
    public String saveAnime(Anime anime, RedirectAttributes redirectAttributes){
        if(animeService.saveOrUpdateAnime(anime)) {
            redirectAttributes.addFlashAttribute("message", "Saved Successfully");
            return "redirect:/viewAnimeList";
        }
        redirectAttributes.addFlashAttribute("message", "Failed to save");
        return "redirect:/addAnime";
    }

    @GetMapping("/editAnime/{id}")
    public String editAnime(@PathVariable Long id, Model model){
        model.addAttribute("anime", animeService.getAnimeById(id));

        return "EditAnime";
    }

    @PostMapping("/editSaveAnime")
    public String editSaveAnime(Anime anime, RedirectAttributes redirectAttributes){
        if(animeService.saveOrUpdateAnime(anime)) {
            redirectAttributes.addFlashAttribute("message", "Edited Successfully");
            return "redirect:/viewAnimeList";
        }
        redirectAttributes.addFlashAttribute("message", "Failed to edit");
        return "redirect:/editAnime/" + anime.getId();
    }

    @GetMapping("/deleteAnime/{id}")
    public String deleteAnime(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if(animeService.deleteAnime(id)) {
            redirectAttributes.addFlashAttribute("message", "Deleted Successfully");
        }
        redirectAttributes.addFlashAttribute("message", "Failed to delete");
        return "redirect:/addAnime";
    }
}
