package com.scm.scm.controllers;

<<<<<<< HEAD
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.helpers.Helper;
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.entities.User;
import com.scm.scm.helpers.Helper;
import com.scm.scm.services.UserService;
>>>>>>> 0ca250c2975d62cf65f5942e34fe50f60d04b410


@Controller
@RequestMapping("/user")
public class UserController {
  private Logger logger=LoggerFactory.getLogger(UserController.class);
<<<<<<< HEAD
=======

  @Autowired
  private UserService userService;
 
>>>>>>> 0ca250c2975d62cf65f5942e34fe50f60d04b410
  //user dashboard page
  @RequestMapping(value="/dashboard")
  public String userDashboard(){
    return "user/dashboard";
  }
  //user add contacts page
  @RequestMapping(value="/profile")
<<<<<<< HEAD
  public String userProfile(Authentication authentication){
    String name=Helper.getEmailLoggedUser(authentication);
    logger.info("User login: {}"+name);
=======
  public String userProfile(Authentication authentication,Model model){
    // String username=Helper.getEmailLoggedUser(authentication);
    // logger.info("User login: {}",username);
    // User user=userService.getUserByEmail(username);

    // logger.info(user.getName());
    // logger.info(user.getEmail()); 
    // model.addAttribute("loggedinUser",user);
>>>>>>> 0ca250c2975d62cf65f5942e34fe50f60d04b410
    return "user/profile";
  }
  //user view contacts
  //user edit contact
  //user delete contact
  //user search contact
}
