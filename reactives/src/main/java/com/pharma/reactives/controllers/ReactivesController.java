package com.pharma.reactives.controllers;

import com.pharma.reactives.dao.ReactiveDAO;
import com.pharma.reactives.models.Reactive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/reactives")
public class ReactivesController {
    private final ReactiveDAO reactiveDAO;

    @Autowired
    public ReactivesController(ReactiveDAO reactiveDAO){
        this.reactiveDAO = reactiveDAO;
    }

    @GetMapping()
    public String getAll(Model model){
        model.addAttribute("reactives", reactiveDAO.getAll());
        return "reactives/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id,
                          Model model){
        model.addAttribute("reactive", reactiveDAO.getById(id));
        return "reactives/show";
    }

    @GetMapping("/new")
    public String newReactive(@ModelAttribute("reactive") Reactive reactive){
        return "reactives/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("reactive") Reactive reactive){
        reactiveDAO.save(reactive);
        return "redirect:/reactives";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model){
        model.addAttribute("reactive", reactiveDAO.getById(id));
        return "reactives/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("reactive") Reactive reactive,
                         @PathVariable("id") int id){
        reactiveDAO.update(id, reactive);
        return "redirect:/reactives";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        reactiveDAO.delete(id);
        return "redirect:/reactives";
    }
}
