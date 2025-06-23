package me.danhthanhf.distant_class.security;

import lombok.RequiredArgsConstructor;
import me.danhthanhf.distant_class.common.constant.ErrorConstants;
import me.danhthanhf.distant_class.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(ErrorConstants.ERR_USER_NOT_FOUND));
        return UserDetailImpl.buildUserDetail(user);
    }
}
