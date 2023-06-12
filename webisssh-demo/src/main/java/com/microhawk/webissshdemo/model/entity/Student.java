package com.microhawk.webissshdemo.model.entity;

import com.microhawk.webissshdemo.model.vo.NameVo;
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
public class Student {

    @Indexed(unique = true)
    private String studentId;
    @Indexed(unique = true)
    private int rollNumber;
    private NameVo studentName;
    private String phoneNumber;
    private String studentEmail;
    private String department;
}
