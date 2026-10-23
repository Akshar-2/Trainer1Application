package com.example.trainer1application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    public static Map<String, String> users = new HashMap<>();
    public static Map<String, String> emailToUser = new HashMap<>();

    @GetMapping("/")
    public String home() { return "redirect:/login"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session) {
        // RULE: Username aani Password khali nasel tar Login hou dya
        // Signup kela asel tar password check kara, nasel tar pan Login hou dya
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return "redirect:/login?error";
        }

        // Jar user adhi Signup ne banla asel aani password chukicha asel tarach adva
        if (users.containsKey(username) && !users.get(username).equals(password)) {
            return "redirect:/login?error";
        }

        // Jar navin user asel tar tyala auto-save kara
        if (!users.containsKey(username)) {
            users.put(username, password);
        }

        session.setAttribute("loggedUser", username);
        return "redirect:/trainers";
    }

    @GetMapping("/signup")
    public String signup() { return "signup"; }

    @PostMapping("/signup")
    public String doSignup(@RequestParam String username,
                           @RequestParam(required = false) String email,
                           @RequestParam String password) {
        users.put(username, password);
        if (email != null && !email.isEmpty()) {
            emailToUser.put(email, username);
        }
        return "redirect:/login";
    }

    @GetMapping("/forgot-password")
    public String forgot() { return "forgot-password"; }

    @PostMapping("/forgot-password")
    public String doForgot(@RequestParam(required = false) String email, Model model) {
        if (email != null && emailToUser.containsKey(email)) {
            String user = emailToUser.get(email);
            model.addAttribute("message", "Username: " + user + " | Password: " + users.get(user));
        } else {
            model.addAttribute("message", "Email not found! Please Sign Up first.");
        }
        return "forgot-password";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}