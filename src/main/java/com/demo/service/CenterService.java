package com.demo.service;

import com.demo.web.dto.CenterDto;
import com.demo.web.dto.request.CenterReq;
import com.demo.web.dto.request.FresherInCenterReq;

import java.util.List;

public interface CenterService {
    List<CenterDto> getAllCenter();

    void createCenter(CenterReq centerReq);

    void updateCenter(String centerId, CenterReq centerReq);

    void deleteCenter(String centerId);

    void addFresherIntoCenter(FresherInCenterReq fresherInCenterReq);
}
