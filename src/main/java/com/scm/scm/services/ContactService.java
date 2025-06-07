package com.scm.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;

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
    // search contact
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,
            User user);
    //get contact by user id
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction);

}
