package com.demo.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FresherByScore {
    private Float score;
    private Integer numOfFresher;
}
