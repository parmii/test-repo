package com.solactive.ticksconsumerservice.service;

import com.solactive.ticksconsumerservice.model.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserCredentials userCredentials;

    public MyUserDetailsService(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    /**
     * This method returns a static user which will be used for authenticaiton
     * @return User Object
     * @throws UsernameNotFoundException if username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new User(userCredentials.getUsername(), userCredentials.getPassword(), getAuthorities(userCredentials.getRole()));
    }

    /**
     * This method returns static authorities
     * @param role ADMIN role
     * @return Collection of GrantedAuthority
     */
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }
}