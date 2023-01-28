package com.jawwad.limitserver.controller;

import com.jawwad.limitserver.pojo.LimitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    LimitData limitData;

    @GetMapping("/data")
    public LimitData getLimitData(){
        return this.limitData;
    }

}
