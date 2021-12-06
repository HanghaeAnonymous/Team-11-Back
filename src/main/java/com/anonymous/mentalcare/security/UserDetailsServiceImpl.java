package com.anonymous.mentalcare.security;


import com.anonymous.mentalcare.models.User;
import com.anonymous.mentalcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserId(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("Can't find " + username);
        }

        return new UserDetailsImpl(user.get());
    }
}
