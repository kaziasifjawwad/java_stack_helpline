package com.jawwad.ss_l3.domain;

import com.jawwad.ss_l3.dto.UserRequest;
import com.jawwad.ss_l3.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class User implements UserDetails {
    private Set<GrantedAuthority> grantedAuthoritySet;
    private String userName;
    private String password;

    public User(UserEntity userEntity) {
        this.userName = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.grantedAuthoritySet =
                new LinkedHashSet<GrantedAuthority>();
    }

    public User(UserRequest userRequest) {
        this.userName = userRequest.getUsername();
        this.password = userRequest.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthoritySet;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
