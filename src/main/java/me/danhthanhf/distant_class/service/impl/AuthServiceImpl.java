package me.danhthanhf.distant_class.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.danhthanhf.distant_class.common.constant.ErrorConstants;
import me.danhthanhf.distant_class.common.constant.SuccessConstants;
import me.danhthanhf.distant_class.common.enums.TokenTypeEnum;
import me.danhthanhf.distant_class.common.event.VerifyEmailEvent;
import me.danhthanhf.distant_class.common.response.ApiResponse;
import me.danhthanhf.distant_class.common.util.JwtUtil;
import me.danhthanhf.distant_class.dto.auth.RegisterRequest;
import me.danhthanhf.distant_class.entity.User;
import me.danhthanhf.distant_class.exception.type.ResourceConflictException;
import me.danhthanhf.distant_class.mapper.UserMapper;
import me.danhthanhf.distant_class.service.AuthService;
import me.danhthanhf.distant_class.service.RedisService;
import me.danhthanhf.distant_class.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserMapper userMapper;
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final JwtUtil jwtUtil;
    private final RedisService redisService;

    @Override
    @Transactional
    public ApiResponse<Void> register(RegisterRequest request) {
        var email = request.getEmail();

        //check if email already exists
        if (userService.isEmailExists(email)) {
            throw new ResourceConflictException(ErrorConstants.ERR_EMAIL_ALREADY_EXISTS);
        }

        User user = userMapper.toUser(request);

        // encode password
        user.setPassword(userService.encodePassword(user.getPassword()));

        // get userId
        user = userService.saveUser(user);

        // send verification email and save code to user entity
        publisher.publishEvent(new VerifyEmailEvent(this, user));

        return ApiResponse.success(SuccessConstants.MSG_VERIFY_EMAIL_SEND);
    }

    @Transactional
    public ApiResponse<Void> verifyEmail(String token) {
        try {
            // can throw exception if token is invalid
            String email = jwtUtil.extractEmail(token);

            // get token from Redis
            String tokenInRedis = redisService.getToken(TokenTypeEnum.VERIFY_TOKEN, email);

            // verify token
            if (tokenInRedis == null || !tokenInRedis.equals(token)) {
                log.error(" token for email: {}", email);
                throw new Exception("Token invalid or expired");
            }

            // set user as verified
            userService.markEmailVerified(email);

            // delete token from Redis
            redisService.deleteToken(TokenTypeEnum.VERIFY_TOKEN, email);

            return ApiResponse.success(SuccessConstants.MSG_EMAIL_VERIFICATION_SUCCESS);
        } catch (Exception e) {
            log.error("Invalid token: {}", e.getMessage());
            return ApiResponse.error(e.getMessage());
        }
    }


}
