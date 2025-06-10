package com.scm.scm.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;
import com.scm.scm.forms.ContactForm;
import com.scm.scm.forms.ContactSearchForm;
import com.scm.scm.helpers.AppConstants;
import com.scm.scm.helpers.Helper;
import com.scm.scm.helpers.Message;
import com.scm.scm.helpers.MessageType;
import com.scm.scm.services.ContactService;
import com.scm.scm.services.ImageService;
import com.scm.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

   private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);
  
  
   @Autowired
  private ImageService imageService;

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
  public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session) {


    if(result.hasErrors()) {
      session.setAttribute("message", Message.builder()
          .content("Please fill all the required fields")
          .type(MessageType.red)
          .build());
      return "user/add_contact";
    }
    //process the form data
    String userName=Helper.getEmailOfLoggedUser(authentication);

    //form-> contact
    User user=userService.getUserByEmail(userName);

    //process the image
    logger.info("Contact image: "+contactForm.getContactImage().getOriginalFilename());
    String fileName=UUID.randomUUID().toString();
    String fileUrl=imageService.uploadImage(contactForm.getContactImage(),fileName);

    Contact contact = new Contact();
    contact.setName(contactForm.getName());
    contact.setEmail(contactForm.getEmail()); 
    contact.setPhoneNumber(contactForm.getPhoneNumber());
    contact.setAddress(contactForm.getAddress());
    contact.setFavorite(contactForm.isFavorite());
    contact.setDescription(contactForm.getDescription());
    contact.setWebsiteLink(contactForm.getWebsiteLink());
    contact.setLinkedInLink(contactForm.getLinkedInLink());
    contact.setCloudinaryImagePublicId(fileName);
    contact.setUser(user);
    contact.setPicture(fileUrl);
    contactService.save(contact);


    //add session message
    session.setAttribute("message", Message.builder()
        .content("Contact added successfully")
        .type(MessageType.green)
        .build());

    System.out.println(contactForm);
     return "redirect:/user/contact/add";
  }

   @RequestMapping("/contacts")
public String viewContacts(
    @RequestParam(value="page", defaultValue="0") int page,
    @RequestParam(value="size", defaultValue="10") int size,
    @RequestParam(value="sortBy", defaultValue="name") String sortBy,
    @RequestParam(value="direction", defaultValue="asc") String direction,
    Model model, Authentication authentication) {
    
    try {
        String userName = Helper.getEmailOfLoggedUser(authentication);
        User user = userService.getUserByEmail(userName);
        Page<Contact> contactPage = contactService.getByUser(user, page, size, sortBy, direction);
        
        model.addAttribute("pageContact", contactPage);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        model.addAttribute("contactSearchForm", new ContactSearchForm());
        
        return "user/contacts";
    } catch (Exception e) {
        logger.error("Error loading contacts", e);
        return "error";
    }
}

   @RequestMapping("/search")
public String searchHandler(
        @ModelAttribute ContactSearchForm contactSearchForm,
        @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
        @RequestParam(value = "direction", defaultValue = "asc") String direction,
        Model model,
        Authentication authentication) {

    logger.info("Search field: {} | keyword: {}", contactSearchForm.getField(), contactSearchForm.getValue());

    var user = userService.getUserByEmail(Helper.getEmailOfLoggedUser(authentication));
    Page<Contact> pageContact;

    // Load default data if field or value is empty
    if (contactSearchForm.getField() == null || contactSearchForm.getField().isBlank()
            || contactSearchForm.getValue() == null || contactSearchForm.getValue().isBlank()) {
        logger.info("No search input provided. Loading all contacts by default.");
        pageContact = contactService.getByUser(user, page, size, sortBy, direction);
    } else {
        // Normal search logic
        if (contactSearchForm.getField().equalsIgnoreCase("name")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction, user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("email")) {
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction, user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("phone")) {
            pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy, direction, user);
        } else {
            pageContact = Page.empty(); // fallback
        }
    }

    model.addAttribute("contactSearchForm", contactSearchForm);
    model.addAttribute("pageContact", pageContact);
    model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

    return "user/search";
}

}
