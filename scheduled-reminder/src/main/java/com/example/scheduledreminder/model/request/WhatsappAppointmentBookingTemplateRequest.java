package com.example.scheduledreminder.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhatsappAppointmentBookingTemplateRequest {

    private String toRecipient;
    private String patientFirstName;
    private String hospitalName;
    private String appointmentStartTime;
    private String patientMrdNumber;
    private String appointmentId;
    private String doctorName;
}
