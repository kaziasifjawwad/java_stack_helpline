package com.jawwad.ss_l3.config;

import com.jawwad.ss_l3.domain.User;
import com.jawwad.ss_l3.entity.UserEntity;
import com.jawwad.ss_l3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createUser(UserDetails user) {
        var userEntity = new UserEntity().setPassword(user.getPassword())
                .setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails user) {
        var existingUser = this.userRepository.findByUsernameAndEnabled(user.getUsername(), 1)
                .orElseThrow();
        existingUser.setPassword(user.getPassword())
                .setUsername(user.getUsername());
        this.userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String username) {
        this.userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userRepository.findByUsernameAndEnabled(username, 1)
                .orElseThrow();
        return new User(user);
    }
}
