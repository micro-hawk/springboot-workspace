package com.example.scheduledreminder.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(value = "master_appointment")
public class MasterAppointment extends EhrBaseMongoEntity {

    private String masterAppointmentId;

    private Boolean isDoctorAssigned;
    private String doctorCode;
    private String doctorRegistrationNumber;

    private String branchCode;
    private String branchName;
    private String hospitalCode;
    private String hospitalName;

    private String patientMrdNumber;

    private Long startTime;
    private Long endTime;

    private boolean isBooked;
    private boolean isUnavailable;
    private String appointmentType;
    private Long checkInTimeStamp;
    private Long paymentTransactionTimeStamp;

    private String parentAppointmentId;
    private boolean needsAppointmentRedistribution;

    private String lastUpdatedByUserEmail;
}
