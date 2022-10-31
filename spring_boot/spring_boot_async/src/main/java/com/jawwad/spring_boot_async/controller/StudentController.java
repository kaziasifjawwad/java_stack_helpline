package com.jawwad.spring_boot_async.controller;

import com.jawwad.spring_boot_async.pojo.StudentResponse;
import com.jawwad.spring_boot_async.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents(){
        return ResponseEntity.ok().body(studentService.getAllStudent());
    }

    @GetMapping("/thread")
    public ResponseEntity<List<StudentResponse>> getAllStudentsByThread() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok().body(studentService.getAllStudentByThread());
    }
}
