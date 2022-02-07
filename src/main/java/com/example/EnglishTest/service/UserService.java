package com.example.EnglishTest.service;

import com.example.EnglishTest.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    private final Map<String, User> users = new HashMap<>(1);
    {
        User user = new User("Mike", "$2a$10$UOats4bIlFlMYBy.yrytOeY/CS9eHaKcCHH2JCQujIEXzOFrruIZW", "ADMIN");
        users.put(user.getUsername(), user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
