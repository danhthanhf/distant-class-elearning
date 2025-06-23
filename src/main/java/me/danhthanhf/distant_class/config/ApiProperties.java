package me.danhthanhf.distant_class.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "api.base")
public class ApiProperties {
    private String path;
    private String url;

    public String[] getWhiteList() {
        return new String[]{
                path + "/auth/login",
                path + "/auth/register",
                path + "/auth/verify",
                path + "/auth/refresh",
                path + "/auth/logout",
                path + "/auth/forgot-password",
                path + "/auth/reset-password",
                path + "/auth/verify-email",
                path + "/auth/verify-email/resend",
        };
    }
}
