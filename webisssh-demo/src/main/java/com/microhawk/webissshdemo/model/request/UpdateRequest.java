package com.microhawk.webissshdemo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {

    private int rollNumber;
    private String phoneNumber;
    private String studentEmail;
}
