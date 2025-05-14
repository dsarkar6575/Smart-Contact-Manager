package com.scm.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
  private Logger logger=LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;
 
  //user dashboard page
  @RequestMapping(value="/dashboard")
  public String userDashboard(){
    return "user/dashboard";
  }
  //user add contacts page
  @RequestMapping(value="/profile")
  public String userProfile(Authentication authentication,Model model){
    // String username=Helper.getEmailLoggedUser(authentication);
    // logger.info("User login: {}",username);
    // User user=userService.getUserByEmail(username);

    // logger.info(user.getName());
    // logger.info(user.getEmail()); 
    // model.addAttribute("loggedinUser",user);
    return "user/profile";
  }
  //user view contacts
  //user edit contact
  //user delete contact
  //user search contact
}