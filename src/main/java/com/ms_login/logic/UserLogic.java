package com.ms_login.logic;

import com.ms_login.dto.UserCreateRequest;
import com.ms_login.dto.UserRequest;
import com.ms_login.exception.UserExistException;
import com.ms_login.repository.UserRepository;
import com.ms_login.services.UserService;
import com.ms_login.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Optional;

@Service
 class UserLogic implements UserService {

    private final UserRepository userRepository;

    public UserLogic(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    public void getUserByEmail(UserRequest userRequest) {
        Optional<User> temp = userRepository.findByEmial(userRequest.getEmail());
        if(temp.isPresent()){

        }
    }

    @Override
    public void saveUser(UserCreateRequest userCreateRequest) {
        Optional<User> temp = userRepository.findByEmial(userCreateRequest.getEmail());

        try {
            if (temp.isPresent()) {
                System.out.println(userCreateRequest.getEmail());
                throw new UserExistException(userCreateRequest.getEmail());
            } else {
                User newUser = new User(userCreateRequest.getUserName(),
                        passwordEncoder().encode(userCreateRequest.getPassword()),
                        userCreateRequest.getEmail(),
                        1,
                        LocalDate.now(),
                        LocalDate.now());
                userRepository.save(newUser);
            }
        }catch (UserExistException ex){
            ex.printStackTrace();
        }
    }


}
