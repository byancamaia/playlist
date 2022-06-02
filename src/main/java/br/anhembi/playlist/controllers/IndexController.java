package br.anhembi.playlist.controllers;

import br.anhembi.playlist.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final StorageService storageService;

    @Autowired
    public IndexController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    public String getHomePage(Model model){

        //MVC
        model.addAttribute("songFileNames", storageService.getSongFileNames());
        return "index";
    }
}
