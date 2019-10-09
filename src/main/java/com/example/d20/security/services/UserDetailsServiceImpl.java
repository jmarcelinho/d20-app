package com.example.d20.security.services;

import com.example.d20.model.User;
import com.example.d20.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	
        User user = userRepository.findByEmail(email)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("User Not Found with -> email : " + email)
        );

        return UserPrinciple.build(user);
    }
}