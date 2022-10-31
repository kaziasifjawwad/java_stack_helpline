package com.jawwad.spring_boot_async.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
@Setter
@Getter
@Accessors(chain = true)
public class StudentRequest {
    private String name;
}
