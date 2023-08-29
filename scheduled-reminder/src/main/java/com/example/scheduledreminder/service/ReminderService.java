package com.example.scheduledreminder.service;

import com.example.scheduledreminder.client.GarudaClient;
import com.example.scheduledreminder.model.entity.MasterAppointment;
import com.example.scheduledreminder.model.request.WhatsappAppointmentBookingTemplateRequest;
import com.example.scheduledreminder.repository.MasterAppointmentRepository;
import com.example.scheduledreminder.utils.CalendarOperations;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class ReminderService {

    @Autowired
    private MasterAppointmentRepository masterAppointmentRepository;
    @Autowired
    private GarudaClient garudaClient;

    public Mono<MasterAppointment> saveMasterAppointment(MasterAppointment masterAppointment) {
        return masterAppointmentRepository.save(masterAppointment)
            .subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> sendAppointmentReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderThreshold = now.plusHours(4); // 4 hours before the appointment

        return masterAppointmentRepository.getUpcomingAppointments(reminderThreshold)
            .flatMap(this::sendReminder)
            .then();
    }

    private Mono<Boolean> sendReminder(MasterAppointment appointment) {
        log.info("Sending reminder for MasterAppointment ID: {}", appointment.getMasterAppointmentId());
        return sendAppointmentDetailsWhatsappMessage(appointment)
            .subscribeOn(Schedulers.boundedElastic());
    }

    private Mono<Boolean> sendAppointmentDetailsWhatsappMessage(MasterAppointment masterAppointment) {
        return garudaClient.sendAppointmentDetailsWhatsappMessage(
                whatsappAppointmentMessageRequestBuild(masterAppointment))
            .map(response -> {
                log.info("WhatsApp message sent for MasterAppointment ID: {}",
                    masterAppointment.getMasterAppointmentId());
                // Modify this based on the actual response from the Garuda service
                return true; // Success
            })
            .onErrorResume(throwable -> {
                log.error("Error sending WhatsApp message for MasterAppointment ID: {}",
                    masterAppointment.getMasterAppointmentId(), throwable);
                // Log or handle the error and return false to indicate failure
                return Mono.just(false);
            });
    }

    private WhatsappAppointmentBookingTemplateRequest whatsappAppointmentMessageRequestBuild(
        MasterAppointment masterAppointment) {
        return WhatsappAppointmentBookingTemplateRequest
            .builder()
            .toRecipient("7043400140")
            .patientFirstName("Vikas Das")
            .hospitalName(masterAppointment.getHospitalName())
            .appointmentStartTime(CalendarOperations.convertEpochIntoDate(masterAppointment.getStartTime()))
            .patientMrdNumber(masterAppointment.getPatientMrdNumber())
            .appointmentId(masterAppointment.getMasterAppointmentId())
            .doctorName("Dr. Aswin Maurya")
            .build();
    }

}
