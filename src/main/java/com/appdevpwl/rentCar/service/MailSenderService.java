package com.appdevpwl.rentCar.service;

import javax.mail.MessagingException;

public interface MailSenderService {
    void sendMail(String to, String subject, String text) throws MessagingException ;
}
