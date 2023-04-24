package com.microhawk.student.service.impl;

import com.microhawk.student.model.entity.Student;
import com.microhawk.student.model.request.AddStudentRequest;
import com.microhawk.student.model.request.UpdateStudentRequest;
import com.microhawk.student.repository.StudentRepository;
import com.microhawk.student.service.api.StudentService;
import com.microhawk.student.utils.IDGenerator;
import com.mongodb.client.result.DeleteResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Mono<Student> saveNewStudent(AddStudentRequest request) {
        return repository.save(requestToEntity(request))
            .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Student> getByStudentId(String studentId) {
        return repository.findByStudentId(studentId)
            .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Student> getByStudentEmail(String studentEmailId) {
        return repository.findByStudentEmail(studentEmailId)
            .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<List<Student>> getAllStudentsByDepartment(String department) {
        return repository.findAllByDepartment(department).collectList()
            .subscribeOn(Schedulers.parallel());
    }

    @Override
    public Mono<Student> updateStudentByStudentId(UpdateStudentRequest request) {
        return repository.findByStudentId(request.getStudentId())
            .flatMap(student -> {
                student.setMobileNumber(request.getStudentMobileNumber());
                student.setAge(request.getAge());
                student.setDepartment(request.getDepartment());
                return repository.save(student);
            }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<DeleteResult> deleteByStudentId(String studentId) {
        return repository.deleteByStudentId(studentId)
            .subscribeOn(Schedulers.boundedElastic());
    }

    private Student requestToEntity(AddStudentRequest request) {
        String id = IDGenerator.generateCode(request.getStudentName(), 4);
        return Student.builder()
            .studentId(id)
            .studentName(request.getStudentName())
            .studentEmailId(request.getStudentEmailId())
            .age(request.getAge())
            .mobileNumber(request.getMobileNumber())
            .department(request.getDepartment())
            .build();
    }
}
