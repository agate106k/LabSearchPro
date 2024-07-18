package com.todo.auth;

import com.todo.entity.MUser;

public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {
    private MUser user;

    public SimpleLoginUser(MUser user) {
        super(user.getEmail(), user.getPassword(), null);
        this.user = user;
    }

    public MUser getUser() {
        return user;
    }
}
