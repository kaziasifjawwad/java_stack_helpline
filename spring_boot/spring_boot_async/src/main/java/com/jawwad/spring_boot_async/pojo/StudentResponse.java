package com.jawwad.spring_boot_async.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class StudentResponse {
    private String name;
    private String guardianName;
}
