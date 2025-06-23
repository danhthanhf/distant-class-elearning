package me.danhthanhf.distant_class.common.listener;

import lombok.RequiredArgsConstructor;
import me.danhthanhf.distant_class.common.constant.AppConstants;
import me.danhthanhf.distant_class.common.event.VerifyEmailEvent;
import me.danhthanhf.distant_class.common.util.JwtUtil;
import me.danhthanhf.distant_class.config.ApiProperties;
import me.danhthanhf.distant_class.config.JwtProperties;
import me.danhthanhf.distant_class.entity.User;
import me.danhthanhf.distant_class.service.MailService;
import me.danhthanhf.distant_class.service.RedisService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyEmailListener {
    private final MailService mailService;
    private final ApiProperties apiProperties;
    private final JwtUtil jwtUtil;
    private final JwtProperties jwtProperties;
    private final RedisService redisService;

    @Async
    @EventListener
    public void handleVerifyEmail(VerifyEmailEvent event) {
        User user = event.getUser();
        String token = jwtUtil.generateVerifyEmailToken(user);
        // create verifyLink with api like http://localhost:8080/api/auth/verify-email?token=...
        String verifyLink = apiProperties.getUrl() + AppConstants.API_AUTH_VERIFY_EMAIL + "?token=" + token;

        // save token to redis with user as key and expiration time
        redisService.saveVerifyToken(user.getEmail(), token, jwtProperties.getVerifyEmailExpiration());

        // send verify email
        mailService.sendVerificationEmail(user.getEmail(), verifyLink);
    }
}
