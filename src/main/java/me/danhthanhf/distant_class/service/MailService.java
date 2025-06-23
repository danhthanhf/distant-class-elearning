package me.danhthanhf.distant_class.service;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface MailService {
    void sendSimpleMessage(String to, String subject, String body);

    void sendHtmlMessage(String to, String subject, String html) throws MessagingException, UnsupportedEncodingException;

    void sendVerificationEmail(String to, String code);

}
