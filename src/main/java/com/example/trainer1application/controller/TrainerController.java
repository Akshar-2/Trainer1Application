package com.example.trainer1application.controller;

import com.example.trainer1application.model.Trainer;
import com.example.trainer1application.repository.TrainerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/trainers")
public class TrainerController {
    @Autowired private TrainerRepository repo;

    @GetMapping public List<Trainer> getAll() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Trainer getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping public Trainer add(@RequestBody Trainer t) { return repo.save(t); }

    @PutMapping("/{id}")
    public Trainer update(@PathVariable Long id, @RequestBody Trainer t) {
        t.setId(id); return repo.save(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}