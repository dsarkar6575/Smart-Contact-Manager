package com.scm.scm.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.scm.entities.User;
import com.scm.scm.helpers.ResourceNotFoundException;
import com.scm.scm.repsitories.UserRepos;
import com.scm.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {
 
  @Autowired
  private UserRepos userRepos;
  private Logger logger=LoggerFactory.getLogger(this.getClass());


  @Override
  public User saveUser(User user) {
    String userId=UUID.randomUUID().toString();  
    user.setUserId(userId);
    return userRepos.save(user);
  }

  @Override
  public Optional<User> getUserById(String id) {
    return userRepos.findById(id);
  }

  @Override
  public Optional<User> updateUser(User user) {
        User user2=userRepos.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setPassword(user.getPassword());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProfilePic(user.getProfilePic());
        user2.setProvider(user.getProvider());
        user2.setEnable(user.isEnable());
        user2.setProviderUserId(user.getProviderUserId());

        User save = userRepos.save(user2);

        return Optional.ofNullable(save);
  }

  @Override
  public void deleteUser(String id) {
    User user2=userRepos.findById(id)
    .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    userRepos.delete(user2);
  }

  @Override
  public boolean isUserExist(String userId) {
    User user2=userRepos.findById(userId).orElse(null);
    return user2!=null? true:false;
  }

  @Override
  public boolean isUserExistByEmail(String email) {
    User user2=userRepos.findByEmail(email).orElse(null);
    return user2!=null? true:false;
  }

  @Override
  public List<User> getAllUser() {
    return userRepos.findAll(); 
  }

}
