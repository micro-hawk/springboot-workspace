package com.example.scheduledreminder.controller;

import com.example.scheduledreminder.model.entity.MasterAppointment;
import com.example.scheduledreminder.model.response.GlobalResponse;
import com.example.scheduledreminder.service.ReminderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ReminderController extends BaseController {

    @Autowired
    private ReminderService reminderService;

    @PostMapping(value = "/save-appointment")
    public Mono<GlobalResponse<MasterAppointment>> saveMasterAppointment(
        @RequestBody MasterAppointment masterAppointment) {
        return reminderService.saveMasterAppointment(masterAppointment).map(this::toGlobalResponse);
    }

    @PostMapping("/trigger-appointment-reminders")
    public Mono<ResponseEntity<String>> triggerAppointmentReminders() {
        return reminderService.sendAppointmentReminders()
            .then(Mono.just(ResponseEntity.ok("Appointment reminders triggered successfully.")));
    }
}
