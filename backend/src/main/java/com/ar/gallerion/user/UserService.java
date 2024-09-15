package com.ar.gallerion.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.gallerion.exception.ApiRequestException;

@Service
class UserService {

    private static final int EXIST = 1;
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean isRegistrationSuccessful(UserModelDTO userModelDTO) {
        // if (userModelDTO.getPassword() == null || userModelDTO.getConfirmPassword()
        // == null) {
        // throw new ApiRequestException("PASSWORDS ARE NULL!");
        // }

        UserModel user = new UserModel();
        user.setUsername(userModelDTO.getUsername());

        if (userRepository.isUsernameExists(user.getUsername()) == EXIST) {
            System.out.println("USERNAME EXISTS!");
            throw new ApiRequestException("USERNAME EXISTS!");
        }

        // if (userModelDTO.getPassword().compareTo(userModelDTO.getConfirmPassword())
        // != 0) {
        // System.out.println("PASSWORDS DIDN'T MATCH!");
        // throw new ApiRequestException("PASSWORDS DIDN'T MATCH!");
        // }

        user.setPassword(userModelDTO.getPassword());
        userRepository.save(user);
        return true;
    }
}
