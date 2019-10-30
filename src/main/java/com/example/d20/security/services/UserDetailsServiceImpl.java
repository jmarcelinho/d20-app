package com.example.d20.security.services;

import com.example.d20.model.Account;
import com.example.d20.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
    	
        Account user = accountRepository.findByEmail(email)
                	.orElseThrow(() -> 
                        new UsernameNotFoundException("User Not Found with -> email : " + email)
        );

        return UserPrinciple.build(user);
    }
}