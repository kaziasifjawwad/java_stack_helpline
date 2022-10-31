package com.jawwad.spring_boot_async.service;

import com.jawwad.spring_boot_async.entity.Guardian;
import com.jawwad.spring_boot_async.entity.Student;
import com.jawwad.spring_boot_async.mapper.StudentMapper;
import com.jawwad.spring_boot_async.pojo.StudentRequest;
import com.jawwad.spring_boot_async.pojo.StudentResponse;
import com.jawwad.spring_boot_async.repository.GuardianRepository;
import com.jawwad.spring_boot_async.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component
public class StudentServiceHelper {

    private final StudentRepository studentRepository;
    private final GuardianRepository guardianRepository;
    private final StudentMapper studentMapper;

    @Async
    public CompletableFuture<List<Student>> getAllStudents(){
        var students = studentRepository.findAll();
        return CompletableFuture.completedFuture(students);
    }

    @Async
    public CompletableFuture<Map<UUID, Guardian>> getAllGuardians(){
        return  CompletableFuture.completedFuture(
                guardianRepository.findAll().stream().collect(Collectors.toMap(m->m.getId(),Function.identity()))
        );
    }
}
