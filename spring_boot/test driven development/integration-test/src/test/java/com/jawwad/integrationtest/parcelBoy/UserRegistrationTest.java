package com.jawwad.integrationtest.parcelBoy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jawwad.integrationtest.TestContainerConfig;
import com.jawwad.integrationtest.domain.StudentRequest;
import com.jawwad.integrationtest.entity.StudentEntity;
import com.jawwad.integrationtest.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("User registration test.")
class UserRegistrationTest extends TestContainerConfig {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("create account")
    @TestExecutionListeners(DataConfiguration.class)
    class StudentCreateTest {
        @ParameterizedTest
        @CsvFileSource(resources = "/student.csv", numLinesToSkip = 1)
        void createStudent(String name, String email, int age) throws Exception {
            StudentRequest request = new StudentRequest().setName(name)
                    .setEmail(email).setAge(age);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.valueOf("application/json"));
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post("/student")
                                    .headers(httpHeaders)
                                    .content(objectMapper.writeValueAsString(request)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
            var entity = studentRepository.findByEmail(email);
            Assertions.assertTrue(entity.isPresent());
        }
    }

    @Nested
    @DisplayName("Duplicate account")
    @TestExecutionListeners(DataConfiguration.class)
    class DuplicateAccountTest {
        @ParameterizedTest
        @CsvFileSource(resources = "/student.csv", numLinesToSkip = 1)
        void shouldThrowException(String name, String email, int age) throws Exception {
            studentRepository.save(new StudentEntity().setAge(age).setEmail(email).setName(name));
            StudentRequest request = new StudentRequest().setName(name)
                    .setEmail(email).setAge(age);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.valueOf("application/json"));
            mockMvc
                    .perform(
                            MockMvcRequestBuilders.post("/student")
                                    .headers(httpHeaders)
                                    .content(objectMapper.writeValueAsString(request)))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andReturn();
        }
    }
}
