package com.oracle.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    //naming should be same as column name in database
    private String user_id;

    private String first_name;

    private String last_name;

    private String dob;

    private String doj;

    private String email_address;

    private String contact_no;

}
