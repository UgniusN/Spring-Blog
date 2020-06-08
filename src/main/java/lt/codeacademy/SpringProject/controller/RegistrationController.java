package lt.codeacademy.SpringProject.controller;

import lt.codeacademy.SpringProject.entities.Article;
import lt.codeacademy.SpringProject.entities.Comment;
import lt.codeacademy.SpringProject.entities.User;
import lt.codeacademy.SpringProject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registracija")
    public String getRegisterForm(Model model) {
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/registracija-submit")
    public String submitRegistration(@ModelAttribute("user") User user, BindingResult errors) {
        userService.createUser(user);
        System.out.println("Labas");
        return "redirect:/prisijungimas";
    }
}
