package com.demo.web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProfileDto {
    private String id;
    private String fullName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String gender;
    private String address;
}
