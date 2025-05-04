package com.scm.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm.entities.User;
import com.scm.scm.forms.UserForm;
import com.scm.scm.helpers.Message;
import com.scm.scm.helpers.MessageType;
import com.scm.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class pagecontroller {

  @Autowired
  private UserService userService;

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

  //Signup controller route
  @GetMapping("/register")
  public String registerPage(Model model){
    UserForm userForm=new UserForm();
      
    model.addAttribute("userForm",userForm);
    return "register";
  }

  @RequestMapping(value="/do-register", method = RequestMethod.POST)
  public String processRegister(@ModelAttribute UserForm userForm,HttpSession session){
    System.out.println("Process Register..");

    System.out.println(userForm);
    //fetch form data
    //send to model
    //validate form data
    //save to database
    //message="Registration Successfull"

    // User user=User.builder()
    // .name(userForm.getName())
    // .email(userForm.getEmail())
    // .phoneNumber(userForm.getPhoneNumber())
    // .password(userForm.getPassword())
    // .about(userForm.getAbout())
    // .profilePic("https://media.licdn.com/media/AAYQAQSOAAgAAQAAAAAAAB-zrMZEDXI2T62PSuT6kpB6qg.png")
    // .build();
    User user=new User();
    user.setName(userForm.getName());
    user.setAbout(userForm.getAbout());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setProfilePic("https://media.licdn.com/media/AAYQAQSOAAgAAQAAAAAAAB-zrMZEDXI2T62PSuT6kpB6qg.png");
    User saveUser=userService.saveUser(user);
    System.out.println("User save;;;;");
    

    //Message ='registration successfull'
    // add the message
    Message message=Message.builder().content("Registration Successfull").type(MessageType.green).build();
     session.setAttribute("message", message);

    //redirect to login page


    return "redirect:/register";
  }
}
