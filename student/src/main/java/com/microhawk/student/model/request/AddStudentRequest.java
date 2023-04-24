package com.microhawk.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequest {

    private String studentName;
    private String mobileNumber;
    private String studentEmailId;
    private int age;
    private String department;
}
