package com.jawwad.ss_l3.config;

import com.jawwad.ss_l3.entity.Authorities;
import com.jawwad.ss_l3.entity.UserEntity;
import com.jawwad.ss_l3.repository.AuthorityRepository;
import com.jawwad.ss_l3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDb implements CommandLineRunner {
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var userName = "jhon";
        userRepository.save(
                new UserEntity().setPassword(passwordEncoder.encode("123")).setUsername(userName).setEnabled(1)
        );
        authorityRepository.save(
                new Authorities().setAuthority("read").setUsername(userName)
        );
    }
}
