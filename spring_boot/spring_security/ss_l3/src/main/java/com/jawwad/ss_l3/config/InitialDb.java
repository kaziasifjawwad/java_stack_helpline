package com.jawwad.ss_l3.config;

import com.jawwad.ss_l3.entity.Authorities;
import com.jawwad.ss_l3.entity.UserEntity;
import com.jawwad.ss_l3.repository.AuthorityRepository;
import com.jawwad.ss_l3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDb implements CommandLineRunner {
    @Autowired private AuthorityRepository authorityRepository;
    @Autowired private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        var userName = "jhon";
        userRepository.save(
                new UserEntity().setPassword("123").setUsername(userName)
        );
        authorityRepository.save(
                new Authorities().setAuthority("read").setUsername(userName)
        );
    }
}
