package com.cpu.utils;

import com.cpu.user.User;

public class SignInUtils {


    private static SignInUtils INSTANCE = new SignInUtils();

    public static SignInUtils getInstance() {
        return INSTANCE;
    }

    private SignInUtils() {

    }

    private User currentUser;


    public void SignIn(User user){
        currentUser=user;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void SignOut(){
        currentUser=null;
    }
}
