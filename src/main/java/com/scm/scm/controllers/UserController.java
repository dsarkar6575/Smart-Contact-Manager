package com.scm.scm.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.helpers.Helper;


@Controller
@RequestMapping("/user")
public class UserController {
  private Logger logger=LoggerFactory.getLogger(UserController.class);
  //user dashboard page
  @RequestMapping(value="/dashboard")
  public String userDashboard(){
    return "user/dashboard";
  }
  //user add contacts page
  @RequestMapping(value="/profile")
  public String userProfile(Authentication authentication){
    String name=Helper.getEmailLoggedUser(authentication);
    logger.info("User login: {}"+name);
    return "user/profile";
  }
  //user view contacts
  //user edit contact
  //user delete contact
  //user search contact
}
