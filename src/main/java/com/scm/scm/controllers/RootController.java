  package com.scm.scm.controllers;

  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.security.core.Authentication;
  import org.springframework.ui.Model;
  import org.springframework.web.bind.annotation.ControllerAdvice;
  import org.springframework.web.bind.annotation.ModelAttribute;

  import com.scm.scm.entities.User;
  import com.scm.scm.helpers.Helper;
  import com.scm.scm.services.UserService;

  @ControllerAdvice
  public class RootController {

    private Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @ModelAttribute
    public void addLoggedinUserInformation(Model model, Authentication authentication){
      if (authentication == null) {
              return;
          }
          
      System.out.println("this is model attribute..");
        String username=Helper.getEmailOfLoggedUser(authentication);
      logger.info("User login: {}",username);
      User user=userService.getUserByEmail(username);

      logger.info(user.getName());
      logger.info(user.getEmail()); 
      model.addAttribute("loggedinUser",user);
    }
  }
