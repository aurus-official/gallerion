package com.ar.gallerion.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginValidationService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public LoginValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = userRepository.findByUsername(username);

        if (userModel == null) {
            throw new UsernameNotFoundException("USERNAME NOT FOUND!");
        }

        return new LoginUser(userModel);
    }
}
