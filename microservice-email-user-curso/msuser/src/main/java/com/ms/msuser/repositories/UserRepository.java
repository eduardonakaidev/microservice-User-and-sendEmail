package com.ms.msuser.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.msuser.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel,UUID>{
    
}
