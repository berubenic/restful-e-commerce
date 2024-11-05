package com.example.assignment2_restful_ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class MainController {

    /**
     * Handle the index page.
     *
     * @param model the model
     * @return the index page
     */
    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("eventName", "FIFA 2018");
        return "index";
    }
}
