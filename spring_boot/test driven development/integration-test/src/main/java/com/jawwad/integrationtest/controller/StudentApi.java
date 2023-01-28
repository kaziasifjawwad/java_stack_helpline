package com.jawwad.integrationtest.controller;

import com.jawwad.integrationtest.domain.StudentRequest;
import com.jawwad.integrationtest.domain.StudentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "student", tags = "student-info")
@RequestMapping(path = "student")
public interface StudentApi {
    @ApiOperation(
            value = "Create new student",
            nickname = "add student",
            notes = "Add new student to the system",
            tags = {
                    "student-info",
            })
    @PostMapping
    ResponseEntity<Void> createNewUser(@RequestBody StudentRequest studentRequest);

    @ApiOperation(
            value = "Get a student by email",
            nickname = "getStudentByEmail",
            notes = "Get student by email",
            tags = {
                    "student-info",
            })
    @GetMapping("{email}")
    ResponseEntity<StudentResponse> getStudentByEmail(@PathVariable String email);


    @ApiOperation(
            value = "Is this student applicable ?",
            nickname = "isApplicable",
            notes = "",
            tags = {
                    "student-info",
            })
    @GetMapping("{is-applicable/email}")
    ResponseEntity<Boolean> isApplicable(@PathVariable String email);
}
