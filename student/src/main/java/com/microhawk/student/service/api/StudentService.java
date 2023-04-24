package com.microhawk.student.service.api;

import com.microhawk.student.model.entity.Student;
import com.microhawk.student.model.request.AddStudentRequest;
import com.microhawk.student.model.request.UpdateStudentRequest;
import com.mongodb.client.result.DeleteResult;
import java.util.List;
import reactor.core.publisher.Mono;

public interface StudentService {

    Mono<Student> saveNewStudent(AddStudentRequest request);

    Mono<Student> getByStudentId(String studentId);

    Mono<Student> getByStudentEmail(String studentEmailId);

    Mono<List<Student>> getAllStudentsByDepartment(String department);
    Mono<Student> updateStudentByStudentId(UpdateStudentRequest request);

    Mono<DeleteResult> deleteByStudentId(String studentId);
}
