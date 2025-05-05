package com.scm.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {

  @NotBlank(message = "Username is required")
  @Size(min=3, message= "min 3 character is required")
  private String name;
  @NotBlank(message = "Email is required")
  @Email(message = "Email id not validate")
  private String email;
  @NotBlank(message = "Password is required")
  @Size(min=6, message="Minimum 6 character is required")
  private String password;
  @NotBlank(message = "Contact number is required")
  @Size(min=10, max=12, message = "Invalid contact number")
  private String phoneNumber;
  @NotBlank(message = "Contact number is required")
  private String about; 
}
