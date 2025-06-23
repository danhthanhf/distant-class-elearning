package me.danhthanhf.distant_class.service;

import me.danhthanhf.distant_class.common.response.ApiResponse;
import me.danhthanhf.distant_class.dto.auth.RegisterRequest;

public interface AuthService {
    ApiResponse<Void> register(RegisterRequest request);

    ApiResponse<Void> verifyEmail(String code);
}
