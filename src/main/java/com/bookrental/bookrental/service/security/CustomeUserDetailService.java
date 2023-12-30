package com.bookrental.bookrental.service.security;

import com.bookrental.bookrental.model.User;
import com.bookrental.bookrental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomeUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // here we load user from datebase
       User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }
}
