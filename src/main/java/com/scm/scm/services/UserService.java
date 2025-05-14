package com.scm.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.scm.entities.User;

public interface UserService {
  User saveUser(User user);
  Optional<User> getUserById(String id);
  Optional<User> updateUser(User user);
  void deleteUser(String id);
  boolean isUserExist(String userId);
  boolean isUserExistByEmail(String email);
  List<User> getAllUser();
<<<<<<< HEAD
=======
  User getUserByEmail(String email);
>>>>>>> 0ca250c2975d62cf65f5942e34fe50f60d04b410
} 