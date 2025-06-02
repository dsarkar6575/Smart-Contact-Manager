package com.scm.scm.repsitories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scm.scm.entities.Contact;
import com.scm.scm.entities.User;

@Repository
public interface ContactRepos extends JpaRepository<Contact, String> {

    //FIND BY USER 
    Page<Contact> findByUser(User user, Pageable pageable);
    //customer query method
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);  
    
}
