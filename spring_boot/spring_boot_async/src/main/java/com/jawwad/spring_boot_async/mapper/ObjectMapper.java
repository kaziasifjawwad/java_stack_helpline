package com.jawwad.spring_boot_async.mapper;

public interface ObjectMapper<T,R> {
     R map(T var1);
}
