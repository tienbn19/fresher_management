package com.demo.web.dto.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class PointReq {
    @Min(value = 0, message = "Invalid point: Less than zero")
    @Max(value = 10, message = "Invalid point: Exceeds 10")
    private Float project1Point;
    @Min(value = 0, message = "Invalid point: Less than zero")
    @Max(value = 10, message = "Invalid point: Exceeds 10")
    private Float project2Point;
    @Min(value = 0, message = "Invalid point: Less than zero")
    @Max(value = 10, message = "Invalid point: Exceeds 10")
    private Float project3Point;
}
