package com.jawwad.spring_boot_async.service;

import com.jawwad.spring_boot_async.mapper.StudentMapper;
import com.jawwad.spring_boot_async.pojo.StudentRequest;
import com.jawwad.spring_boot_async.pojo.StudentResponse;
import com.jawwad.spring_boot_async.repository.GuardianRepository;
import com.jawwad.spring_boot_async.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GuardianRepository guardianRepository;
    private final StudentMapper studentMapper;

    public StudentResponse saveStudent(StudentRequest request){
        return studentMapper.entityToDomain()
                .map(studentRepository
                        .save(studentMapper
                                .domainToEntity()
                                .map(request)
                        )
                );
    }

    public List<StudentResponse> getAllStudent(){
        var students = studentRepository.findAll();

        var guardiansId = students.stream().map(m->m.getGuardianId()).collect(Collectors.toList());
        System.out.println(guardiansId);
        var guardians = guardianRepository.findByIdIn(guardiansId);
        System.out.println(guardians);
        var guardianHash = guardians.stream().collect(Collectors.toMap(m->m.getId(), Function.identity()));

        return studentRepository.findAll()
                .stream()
                .map(m->studentMapper
                        .entityToDomain().map(m)
                .setGuardianName(guardianHash.get(m.getGuardianId())
                        .getName()))
                .collect(Collectors.toList());
    }
}
