package com.photos.api.security;

import com.photos.api.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.photos.api.models.User user = userRepository.findByEmail(email);
        // TODO: 2018-04-21 passwordEncoder
        return new User(user.getEmail(), "{noop}" + user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole()));
    }
}
