package com.jawwad.spring_boot_async.mapper;

import com.jawwad.spring_boot_async.entity.Student;
import com.jawwad.spring_boot_async.pojo.StudentRequest;
import com.jawwad.spring_boot_async.pojo.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentMapper {

    public ObjectMapper<StudentRequest, Student> domainToEntity(){
        return domain->{
          Student student = new Student();
          student.setName(domain.getName());
          return student;
        };
    }

    public ObjectMapper<Student, StudentResponse> entityToDomain(){
        return entity->{
            StudentResponse response = new StudentResponse();
            response.setName(entity.getName());
            return response;
        };
    }
}
