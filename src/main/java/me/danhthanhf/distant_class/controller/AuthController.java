package me.danhthanhf.distant_class.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import me.danhthanhf.distant_class.common.constant.ErrorConstants;
import me.danhthanhf.distant_class.common.constant.SuccessConstants;
import me.danhthanhf.distant_class.common.response.ApiResponse;
import me.danhthanhf.distant_class.dto.auth.RegisterRequest;
import me.danhthanhf.distant_class.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.base.path}/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody @Valid RegisterRequest request) {
        // Implement registration logic here
        authService.register(request);
        return ResponseEntity.ok(ApiResponse.success(SuccessConstants.MSG_REGISTER_SUCCESS));
    }

    // verify-email?token=your_token_here
    @PostMapping("/verify-email")
    public ResponseEntity<ApiResponse<Void>> verifyEmail(@RequestParam @NotBlank(message = ErrorConstants.ERR_VERIFY_TOKEN_REQUIRED) String token) {
        return ResponseEntity.ok(authService.verifyEmail(token));
    }
}
