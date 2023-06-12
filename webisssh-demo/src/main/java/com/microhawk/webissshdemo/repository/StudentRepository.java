package com.microhawk.webissshdemo.repository;

import com.microhawk.webissshdemo.model.entity.Student;
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

    public Mono<Student> findByStudentRollNumber(int rollNumber) {
        Query query = new Query(Criteria.where("rollNumber").is(rollNumber));
        return mongoTemplate.findOne(query, Student.class);
    }

    public Flux<Student> findAll() {
        return mongoTemplate.findAll(Student.class);
    }

    public Mono<Student> deleteByStudentRollNumber(int rollNumber) {
        Query query = new Query(Criteria.where("rollNumber").is(rollNumber));
        return mongoTemplate.findAndRemove(query, Student.class);
    }
}
