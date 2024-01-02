package com.ms.msemail.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.msemail.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel,UUID>{
    
}
