package com.example.scheduledreminder.repository;

import com.example.scheduledreminder.model.entity.MasterAppointment;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MasterAppointmentRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public Mono<MasterAppointment> save(MasterAppointment masterAppointment) {
        return mongoTemplate.save(masterAppointment);
    }

    public Flux<MasterAppointment> getUpcomingAppointments(LocalDateTime reminderThreshold) {
        Query query = new Query();
        query.addCriteria(Criteria.where("startTime")
            .gt(reminderThreshold.minusHours(4))
            .lt(reminderThreshold));

        return mongoTemplate.find(query, MasterAppointment.class);
    }
}
