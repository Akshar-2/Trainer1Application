package com.example.trainer1application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trainers")
public class TrainerViewController {

    // 0=name, 1=email, 2=phone, 3=specialization, 4=bio, 5=status
    private static List<String[]> trainers = new ArrayList<>();

    // 1. Dashboard
    @GetMapping
    public String list(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("trainers", trainers);
        model.addAttribute("username", session.getAttribute("loggedUser"));
        return "dashboard";
    }

    // 2. Add Form ughadnyasathi
    @GetMapping("/add")
    public String addForm(HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        return "add-trainer";
    }

    // 3. Form cha data save karnyasathi - HA TUMCHYA PHOTO MADHE CHUKICHA AAHE
    @PostMapping("/add")
    public String save(@RequestParam String name,
                       @RequestParam String email,
                       @RequestParam String phone,
                       @RequestParam(required = false) String specialization,
                       @RequestParam(required = false) String bio,
                       @RequestParam(required = false) String status,
                       HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        if (status == null || status.isEmpty()) {
            status = "Active";
        }
        trainers.add(new String[]{name, email, phone, specialization, bio, status});
        return "redirect:/trainers";
    }

    // 4. Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        if (id >= 0 && id < trainers.size()) {
            trainers.remove(id);
        }
        return "redirect:/trainers";
    }
}