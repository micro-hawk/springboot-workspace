package com.microhawk.webissshdemo.service;

import com.microhawk.webissshdemo.model.ApiResponseStatus;
import com.microhawk.webissshdemo.model.entity.Student;
import com.microhawk.webissshdemo.model.exception.BusinessException;
import com.microhawk.webissshdemo.model.request.StudentRequest;
import com.microhawk.webissshdemo.model.request.UpdateRequest;
import com.microhawk.webissshdemo.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class StudentImpl {

    @Autowired
    private StudentRepository repository;

    public Mono<Student> saveNewStudent(StudentRequest request) {
        return repository.save(requestToEntity(request))
            .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<List<Student>> getAllStudent() {
        return repository.findAll().collectList()
            .subscribeOn(Schedulers.parallel());
    }

    public Mono<Student> updateStudent(UpdateRequest request) {
        return repository.findByStudentRollNumber(request.getRollNumber())
            .switchIfEmpty(Mono.error(new BusinessException(ApiResponseStatus.STUDENT_NOT_FOUND)))
            .flatMap(student -> {
                student.setStudentEmail(request.getStudentEmail());
                student.setPhoneNumber(request.getPhoneNumber());
                return repository.save(student);
            }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Student> deleteStudentByRollNumber(int rollNumber) {
        return repository.deleteByStudentRollNumber(rollNumber)
            .subscribeOn(Schedulers.boundedElastic());
    }

    private Student requestToEntity(StudentRequest request) {
        String deptCode = HumanReadableIdGenerator.generateCode(request.getDepartment(), 3);
        String studentId = deptCode + HumanReadableIdGenerator.getBase36(4);
        return Student.builder()
            .studentId(studentId)
            .rollNumber(request.getRollNumber())
            .studentName(request.getStudentName())
            .phoneNumber(request.getPhoneNumber())
            .studentEmail(request.getStudentEmail())
            .department(request.getDepartment())
            .build();
    }
}
