package com.demo.service;

import com.demo.web.dto.FresherDto;
import com.demo.web.dto.request.FresherCriteria;
import com.demo.web.dto.request.FresherReq;
import com.demo.web.dto.request.PointReq;

import java.util.List;

public interface FresherService {
    List<FresherDto> getAllFresher();

    void createFresher(FresherReq fresherReq);

    void updateFresherInfo(String fresherId, FresherReq fresherReq);

    void deleteFresher(String fresherId);

    FresherDto calculatePoint(String fresherId, PointReq pointReq);

    List<FresherDto> searchFresher(FresherCriteria fresherCriteria);
}
