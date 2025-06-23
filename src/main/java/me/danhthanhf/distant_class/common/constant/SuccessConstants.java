package me.danhthanhf.distant_class.common.constant;

public class SuccessConstants {
    // auth
    public static final String MSG_REGISTER_SUCCESS = "Registration successful";
    public static final String MSG_VERIFY_EMAIL_SEND = "Verification was sent to your email";
    public static final String MSG_LOGIN_SUCCESS = "Login successful";
    public static final String MSG_LOGOUT_SUCCESS = "Logout successful";
    public static final String MSG_EMAIL_VERIFICATION_SUCCESS = "Email verification successful";

    private SuccessConstants() {
        throw new IllegalStateException(ErrorConstants.ERR_UTILITY_CLASS);
    }


}
