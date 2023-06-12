package com.microhawk.webissshdemo.model.vo;

import com.microhawk.webissshdemo.model.enums.NameTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameVo {

    private NameTitle nameTitle;
    private String firstName;
    private String middleName;
    private String lastName;

}
