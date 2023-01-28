package com.jawwad.integrationtest.mapper;

import com.jawwad.integrationtest.domain.StudentRequest;
import com.jawwad.integrationtest.domain.StudentResponse;
import com.jawwad.integrationtest.entity.StudentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {
    public StudentEntity requestToEntity(StudentRequest studentRequest) {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(studentRequest, studentEntity);
        return studentEntity;
    }

    public StudentResponse entityToResponse(StudentEntity studentEntity) {
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(studentEntity, studentResponse);
        return studentResponse;
    }
}
