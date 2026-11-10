package com.capstone.serviceClasses;

public class PasswordValidationService {

  /**
   * Checks whether a password meets the requirements.
   * Password must contain at least 5 characters and at least one special character.
   */
  public boolean isValidPassword(String password) {

      if (password == null || password.length() < 5) {
          return false;
      }
      return password.matches(".*[^a-zA-Z0-9].*");
  }

}