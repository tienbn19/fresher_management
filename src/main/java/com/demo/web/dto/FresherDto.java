package com.demo.web.dto;

import lombok.Data;

@Data
public class FresherDto {
    private String id;
    private ProfileDto profileDto;
    private String program;
    private String email;
    private Float project1Point;
    private Float project2Point;
    private Float project3Point;
    private Float avgPoint;
    private CenterDto centerDto;
}
