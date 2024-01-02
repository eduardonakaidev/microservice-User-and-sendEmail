package com.ms.msuser.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.msuser.models.UserModel;
import com.ms.msuser.producers.UserProducer;
import com.ms.msuser.repositories.UserRepository;



@Service
public class UserService {
    
    final UserRepository userRepository;
    final UserProducer userProducer;

    UserService(UserRepository userRepository,UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel){

        userModel =  userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
