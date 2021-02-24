package com.appdevpwl.rentCar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutUsController {

    @RequestMapping("/about-us")
    public String showAboutUsPage() {
        return "about-us";

    }
}
