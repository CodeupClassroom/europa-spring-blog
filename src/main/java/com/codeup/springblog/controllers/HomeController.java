package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String landing() {
        return  "This is the landing page!";
    }

    @GetMapping("/home")
    public String welcome() {
        return "home";
    }

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "dice/roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDiceGuess(@PathVariable int guess, Model model){
        String message;

        int random = (int) Math.ceil(Math.random() * 6);

        if (random == guess) {
            message = "You guessed the random number!";
        } else {
            message = "Sorry, try again.";
        }

        model.addAttribute("message", message);
        model.addAttribute("guess", guess);
        model.addAttribute("random", random);

        return "dice/roll-dice-guess";
    }
}
