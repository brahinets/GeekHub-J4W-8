package org.geekhub.lesson20.service;

import org.geekhub.lesson20.db.persistence.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userService.findBy(username);
        if (optionalUser.isPresent()) {
            final User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
            );
        } else {
            throw new UsernameNotFoundException("Incorrect login or password.");
        }
    }
}
