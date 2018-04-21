package com.photos.api.services;

import com.photos.api.enums.Role;
import com.photos.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version x
 */

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        com.photos.api.models.User user = userRepository.findFirstByEmail(s);

        return new User(user.getEmail(), "{noop}" + user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole()));
    }
}
