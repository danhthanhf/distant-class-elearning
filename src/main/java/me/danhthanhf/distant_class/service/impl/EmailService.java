package me.danhthanhf.distant_class.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import me.danhthanhf.distant_class.common.constant.AppConstants;
import me.danhthanhf.distant_class.common.constant.ErrorConstants;
import me.danhthanhf.distant_class.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailService implements MailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendSimpleMessage(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(fromEmail);
        message.setSubject(subject);
        message.setTo(body);
        mailSender.send(message);
    }

    @Override
    public void sendHtmlMessage(String to, String subject, String html) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        // enable multipart support to send HtML, attachments, etc.

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setTo(to);
        helper.setFrom(new InternetAddress(fromEmail, "Distant Class"));
        helper.setSubject(subject);
        helper.setText(html, true);
        mailSender.send(message);
    }

    private String buildContentHtmlSendVerify(String userName, String verifyLink) {
        Context context = new Context();
        context.setVariable(AppConstants.USERNAME_VARIABLE, userName);
        context.setVariable(AppConstants.VERIFY_LINK_VARIABLE, verifyLink);
        return templateEngine.process(AppConstants.TEMPLATE_NAME, context);
    }

    @Override
    public void sendVerificationEmail(String to, String link) {
        var htmlContent = buildContentHtmlSendVerify(to, link);
        try {
            sendHtmlMessage(to, AppConstants.VERIFY_EMAIL_SUBJECT, htmlContent);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(ErrorConstants.ERR_MAIL_SEND_HTML_FAILED, e);
        }
    }
}
