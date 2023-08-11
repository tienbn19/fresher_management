package com.demo.web.dto.response;

import com.demo.web.dto.CenterDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FresherOfCenterRes {
    private CenterDto centerDto;
    private Integer numOfFresher;
}
