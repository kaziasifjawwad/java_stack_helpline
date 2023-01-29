package com.jawwad.integrationtest.parcelBoy;

import com.jawwad.integrationtest.repository.StudentRepository;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class DataConfiguration implements TestExecutionListener {
    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        var studentRepository = testContext.getApplicationContext().getBean(StudentRepository.class);
        studentRepository.deleteAll();
    }
}
