package com.sac.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingController {
    @RequestMapping("/landing")
    public String landing(){
        return "landing";
    }
}
