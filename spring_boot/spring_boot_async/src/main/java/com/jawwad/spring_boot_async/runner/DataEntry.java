package com.jawwad.spring_boot_async.runner;

import com.jawwad.spring_boot_async.entity.Guardian;
import com.jawwad.spring_boot_async.entity.Student;
import com.jawwad.spring_boot_async.repository.GuardianRepository;
import com.jawwad.spring_boot_async.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataEntry implements CommandLineRunner {

    private final GuardianRepository guardianRepository;
    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        int i = 0;
        while (i <= 10000) {
            Guardian guardian = new Guardian();
            guardian.setName("guardian " + i);

            Student student = new Student();
            student.setName("student" + i);

            var guardianId = guardianRepository.save(guardian).getId();
            student.setGuardianId(guardianId);
            studentRepository.save(student);
            i++;
        }
    }
}
