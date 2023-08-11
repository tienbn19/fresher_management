package com.demo.service.impl;

import com.demo.model.Fresher;
import com.demo.repository.FresherRepository;
import com.demo.service.StatisticalService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.CenterDto;
import com.demo.web.dto.response.FresherByScore;
import com.demo.web.dto.response.FresherOfCenterRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticalServiceImpl implements StatisticalService {
    private final FresherRepository fresherRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<FresherOfCenterRes> fresherOfCenter() {
        var dataGroup = fresherRepository.findAll()
                .stream().filter(e -> e.getCenter() != null)
                .collect(Collectors.groupingBy(Fresher::getCenter))
                .entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        List<FresherOfCenterRes> res = new ArrayList<>();
        dataGroup.forEach((key, value) -> res.add(new FresherOfCenterRes(
                mappingHelper.map(key, CenterDto.class), value)));
        return res;
    }

    @Override
    public List<FresherByScore> fresherByScore() {
        var dataGroup = fresherRepository.findAll()
                .stream().filter(e -> e.getAvgPoint() != null)
                .collect(Collectors.groupingBy(Fresher::getAvgPoint))
                .entrySet().stream()
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().size()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
        List<FresherByScore> res = new ArrayList<>();
        dataGroup.forEach((key, value) -> res.add(new FresherByScore(key, value)));
        return res;
    }
}
