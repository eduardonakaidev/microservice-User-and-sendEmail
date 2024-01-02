package com.ms.msemail.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.msemail.enums.StatusEmail;
import com.ms.msemail.models.EmailModel;
import com.ms.msemail.repositories.EmailRepository;

@Service
public class EmailService {
    final EmailRepository emailRepository;
    final JavaMailSender eMailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender eMailSender) {
        this.emailRepository = emailRepository;
        this.eMailSender = eMailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            eMailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }
            return emailRepository.save(emailModel);
        
    }
}
