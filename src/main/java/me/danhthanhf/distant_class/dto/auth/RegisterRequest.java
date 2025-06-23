package me.danhthanhf.distant_class.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import me.danhthanhf.distant_class.common.constant.AppConstants;
import me.danhthanhf.distant_class.common.constant.ErrorConstants;

@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = ErrorConstants.ERR_NOT_BLANK)
    @Email(message = ErrorConstants.ERR_EMAIL_INVALID)
    private String email;

    @NotBlank(message = ErrorConstants.ERR_NOT_BLANK)
    @Size(min = 8, max = 32, message = ErrorConstants.ERR_PASSWORD_LENGTH)
    @Pattern(regexp = AppConstants.PASSWORD_REGEX, message = ErrorConstants.ERR_PASSWORD_FORMAT)
    private String password;

    @NotBlank(message = ErrorConstants.ERR_NOT_BLANK)
    private String firstName;
    @NotBlank(message = ErrorConstants.ERR_NOT_BLANK)
    private String lastName;
}
