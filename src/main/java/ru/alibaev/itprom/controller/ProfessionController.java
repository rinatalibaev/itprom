package ru.alibaev.itprom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alibaev.itprom.entity.Profession;
import ru.alibaev.itprom.service.ProfessionService;

import java.util.List;

@Controller
@RequestMapping("/profession")
public class ProfessionController {
    private ProfessionService professionService;

    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping("/getAll")
    public String getAllProfessions (Model model) {
        return "/profession/professions";
    }

    @PostMapping("/getAll")
    public @ResponseBody
    List<Profession> getAllProfessionsPost () {
        return professionService.getAll();
    }


    @GetMapping("/get")
    public String getProfession (@RequestParam Long professionId, Model model) {
        model.addAttribute("profession", professionService.get(professionId));
        return "/profession/profession";
    }

    @GetMapping("/create")
    public String createNewProfession (Model model) {
        return "/profession/new_profession";
    }

    @PostMapping("/create")
    public @ResponseBody void createProfession (@RequestBody Profession profession) {
        professionService.create(profession);
    }

    @PostMapping("/edit")
    public @ResponseBody void editProfession (@RequestBody Profession profession) {
        professionService.update(profession);
    }

    @PostMapping("/delete")
    public @ResponseBody void deleteProfession (@RequestParam Long professionId) {
        professionService.delete(professionId);
    }
}
