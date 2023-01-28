package com.jawwad.integrationtest.service;

import com.jawwad.integrationtest.domain.StudentRequest;
import com.jawwad.integrationtest.domain.StudentResponse;
import com.jawwad.integrationtest.exceptionhandler.customexception.UserAlreadyExists;
import com.jawwad.integrationtest.mapper.StudentMapper;
import com.jawwad.integrationtest.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public void saveStudent(StudentRequest studentRequest) {
        if (studentRepository.existsByEmail(studentRequest.getEmail())) throw new UserAlreadyExists(studentRequest.getEmail());
        studentRepository.save(studentMapper.requestToEntity(studentRequest));
    }

    public StudentResponse getStudentByEmail(String email) {
        return studentRepository.findByEmail(email).map(studentMapper::entityToResponse).orElseThrow();
    }

    public boolean isApplicable(String email) {
        var age = studentRepository.findByEmail(email).orElseThrow().getAge();
        return age > 18;
    }
}
