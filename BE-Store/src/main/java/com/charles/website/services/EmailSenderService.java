package com.charles.website.services;

import javax.mail.MessagingException;

public interface EmailSenderService {
    void sendEmail(String toEmail,
                   String subject,
                   String body) throws MessagingException;
}
