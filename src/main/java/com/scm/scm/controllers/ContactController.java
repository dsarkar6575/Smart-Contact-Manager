package com.scm.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.helpers.Helper;
import com.scm.scm.services.ContactService;
import com.scm.scm.services.UserService;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

  @Autowired
  private ContactService contactService;

  @Autowired
  private UserService userService;

  @RequestMapping("/add")
  public String addContactView(Model model) {
    ContactForm contactForm = new ContactForm();
    // contactForm.setName("John Doe");
    // contactForm.setFavorite(true);
    model.addAttribute("contactForm", contactForm);   
    return "user/add_contact";
  }

  @RequestMapping(value = "/add",method = RequestMethod.POST)
  public String saveContact(@ModelAttribute ContactForm contactForm, Authentication authentication) {

    //process the form data
    String userName=Helper.getEmailLoggedUser(authentication);

    //form-> contact
    User user=userService.getUserByEmail(userName);

    Contact contact = new Contact();
    contact.setName(contactForm.getName());
    contact.setEmail(contactForm.getEmail()); 
    contact.setPhoneNumber(contactForm.getPhoneNumber());
    contact.setAddress(contactForm.getAddress());
    contact.setFavorite(contactForm.isFavorite());
    contact.setDescription(contactForm.getDescription());
    contact.setWebsiteLink(contactForm.getWebsiteLink());
    contact.setLinkedInLink(contactForm.getLinkedInLink());
    contact.setUser(user);
    contactService.save(contact);
    System.out.println(contactForm);
     return "redirect:/user/contact/add";
  }
}
