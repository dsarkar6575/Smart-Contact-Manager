package com.scm.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

  //user dashboard page
  @RequestMapping(value="/dashboard")
  public String userDashboard(){
    return "user/dashboard";
  }
  //user add contacts page
  @RequestMapping(value="/profile")
  public String userProfile(){
    return "user/profile";
  }
  //user view contacts
  //user edit contact
  //user delete contact
  //user search contact
}
