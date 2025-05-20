package com.scm.scm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scm.scm.entities.Contact;

@Service
public interface ContactService {
  
    //save contact
    Contact save(Contact contact);
    //update contact
    Contact update(Contact contact);
    //delete contact
    void delete(String id);
    //get contact
    List<Contact> getAll();
    //get contact by id
    Contact getById(String id);
    //search contact 
    List<Contact> search(String name, String email, String phone);
    //get contact by user id
    List<Contact> getByUserId(String userId);

}
