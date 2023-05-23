package com.example.mobile_lab_20521133.Common;

public class Validate {
    public static boolean ValidateUsername(String username){
        String usernameRegex = "^[a-zA-Z]{6,}$";
        if(username.matches(usernameRegex)){
            return true;
        }
        else{
            return  false;
        }
    }
    public static boolean ValidatePassword(String password){
        String passwordRegex = "^{6,}$";
        if(password.matches(passwordRegex)){
            return true;
        }
        else{
            return  false;
        }
    }
}
