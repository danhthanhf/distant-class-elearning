package me.danhthanhf.distant_class.service;

import me.danhthanhf.distant_class.entity.User;

public interface UserService {
    boolean existsById(Long userId);

    boolean isEmailExists(String email);

    String encodePassword(String password);

    User saveUser(User user);

    User getUserByEmail(String email);

    void markEmailVerified(String email);
}
