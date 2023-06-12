package com.microhawk.webissshdemo.model.request;

import com.microhawk.webissshdemo.model.vo.NameVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private int rollNumber;
    private NameVo studentName;
    private String phoneNumber;
    private String studentEmail;
    private String department;
}
