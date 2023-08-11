package com.demo.service;

import com.demo.web.dto.response.FresherByScore;
import com.demo.web.dto.response.FresherOfCenterRes;

import java.util.List;

public interface StatisticalService {
    List<FresherOfCenterRes> fresherOfCenter();

    List<FresherByScore> fresherByScore();
}
