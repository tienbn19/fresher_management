package com.demo.web.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ProfileReq {
    private String fullName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String gender;
    private String address;
}
