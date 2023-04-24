package com.microhawk.student.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {

    private String studentId;
    private String studentMobileNumber;
    private int age;
    private String department;
}
