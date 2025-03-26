package com.scm.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pagecontroller {

  @RequestMapping("/home")
  public String home(){
    System.out.println("Home page handler");
    return "home";
  }
}
