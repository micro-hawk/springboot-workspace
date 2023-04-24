package com.microhawk.student.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(value = "students")
public class Student extends BaseEntity {

    @Indexed
    private String studentId;
    private String studentName;
    @Indexed
    private String studentEmailId;
    private String mobileNumber;
    private int age;
    private String department;
}
