package com.microhawk.student.repository;

import com.microhawk.student.model.entity.Student;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StudentRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public Mono<Student> save(Student student) {
        return mongoTemplate.save(student);
    }

    public Mono<Student> findByStudentId(String studentId) {
        Query query = new Query(Criteria.where("studentId").is(studentId));
        return mongoTemplate.findOne(query, Student.class);
    }

    public Mono<Student> findByStudentEmail(String studentEmailId) {
        Query query = new Query(Criteria.where("studentEmailId").is(studentEmailId));
        return mongoTemplate.findOne(query, Student.class);
    }

    public Flux<Student> findAllByDepartment(String dept) {
        Query query = new Query(Criteria.where("department").is(dept));
        return mongoTemplate.find(query, Student.class);
    }

    public Mono<DeleteResult> deleteByStudentId(String studentId) {
        Query query = new Query(Criteria.where("studentId").is(studentId));
        return mongoTemplate.remove(query, Student.class);
    }
}
