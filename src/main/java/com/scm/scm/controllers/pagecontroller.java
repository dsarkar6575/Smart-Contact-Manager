package com.scm.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class pagecontroller {
  //Home controler  route
  @RequestMapping("/home")
  public String home(Model model){
    System.out.println("Home page handler");
    model.addAttribute("name","substring");
    return "home";
  }

  // About controller route
  @RequestMapping("/about")
  public String aboutPage(){
    System.out.println("This is about page..");
    return "about";
  }

  //Service controller route
  @RequestMapping("/service")
  public String servicePage(){
    System.out.println("This is service page..");
    return "service";
  }


    //contact controller route
    @RequestMapping("/contact")
    public String contactPage(){
      System.out.println("This is contact page..");
      return "contact";
    }

      //login controller route
  @GetMapping("/login")
  public String loginPage(){
    System.out.println("This is login page..");
    return "login";
  }

  @GetMapping("/register")
  public String registerPage(){
    return "register";
  }
}
