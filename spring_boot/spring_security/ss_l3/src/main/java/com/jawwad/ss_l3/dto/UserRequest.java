package com.jawwad.ss_l3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserRequest {
    private String password;
    private String username;
}
