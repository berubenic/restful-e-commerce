package com.example.assignment2_restful_ecommerce;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class CustomErrorController implements ErrorController {

    /**
     * Handle the error.
     *
     * @return the error page
     */
    @RequestMapping("/error")
    public String handleError() {
        return "404";
    }
}
