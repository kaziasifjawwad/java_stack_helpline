package com.jawwad.integrationtest.controller;

import com.jawwad.integrationtest.domain.StudentRequest;
import com.jawwad.integrationtest.domain.StudentResponse;
import com.jawwad.integrationtest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StudentController implements StudentApi{

    private final StudentService studentService;

    @Override
    public ResponseEntity<StudentResponse> getStudentByEmail(String email) {
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }

    @Override
    public ResponseEntity<Boolean> isApplicable(String email) {
        return ResponseEntity.ok(studentService.isApplicable(email));
    }

    @Override
    public ResponseEntity<Void> createNewUser(StudentRequest studentRequest) {
        studentService.saveStudent(studentRequest);
        return ResponseEntity.ok().build();
    }
}
