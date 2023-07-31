package com.demo.service.impl;

import com.demo.model.Fresher;
import com.demo.model.Profile;
import com.demo.repository.FresherRepository;
import com.demo.repository.ProfileRepository;
import com.demo.service.FresherService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.CenterDto;
import com.demo.web.dto.FresherDto;
import com.demo.web.dto.ProfileDto;
import com.demo.web.dto.request.FresherCriteria;
import com.demo.web.dto.request.FresherReq;
import com.demo.web.dto.request.PointReq;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FresherServiceImpl implements FresherService {
    private final FresherRepository fresherRepository;
    private final ProfileRepository profileRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<FresherDto> getAllFresher() {
        return fresherRepository.findAll()
                .stream().map(e -> {
                    var res = mappingHelper.map(e, FresherDto.class);
                    res.setProfileDto(mappingHelper.map(e.getProfile(), ProfileDto.class));
                    if (e.getCenter() != null)
                        res.setCenterDto(mappingHelper.map(e.getCenter(), CenterDto.class));
                    return res;
                }).collect(Collectors.toList());
    }

    @Override
    public void createFresher(FresherReq fresherReq) {
        var profile = mappingHelper.map(fresherReq.getProfileReq(), Profile.class);
        profileRepository.save(profile);
        var fresher = mappingHelper.map(fresherReq, Fresher.class);
        fresher.setProfile(profile);
        fresherRepository.save(fresher);
    }

    @Override
    public void updateFresherInfo(String fresherId, FresherReq fresherReq) {
        fresherRepository.findById(fresherId)
                .map(e -> {
                    mappingHelper.mapIfSourceNotNullAndStringNotBlank(fresherReq, e);
                    mappingHelper.mapIfSourceNotNullAndStringNotBlank(fresherReq.getProfileReq(), e.getProfile());
                    profileRepository.save(e.getProfile());
                    fresherRepository.save(e);
                    return e;
                })
                .orElseThrow(() -> new EntityNotFoundException(Fresher.class.getName(), fresherId));
    }

    @Override
    public void deleteFresher(String fresherId) {
        var fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new EntityNotFoundException(Fresher.class.getName(), fresherId));
        profileRepository.delete(fresher.getProfile());
        fresherRepository.delete(fresher);
    }

    @Override
    public FresherDto calculatePoint(String fresherId, PointReq pointReq) {
        return fresherRepository.findById(fresherId)
                .map(e -> {
                    mappingHelper.mapIfSourceNotNullAndStringNotBlank(pointReq, e);
                    e.setAvgPoint((pointReq.getProject1Point() + pointReq.getProject2Point()
                            + pointReq.getProject3Point()) / 3);
                    fresherRepository.save(e);
                    var res = mappingHelper.map(e, FresherDto.class);
                    res.setProfileDto(mappingHelper.map(e.getProfile(), ProfileDto.class));
                    if (e.getCenter() != null)
                        res.setCenterDto(mappingHelper.map(e.getCenter(), CenterDto.class));
                    return res;
                })
                .orElseThrow(() -> new EntityNotFoundException(Fresher.class.getName(), fresherId));
    }

    @Override
    public List<FresherDto> searchFresher(FresherCriteria fresherCriteria) {
        return fresherRepository.findAll(fresherCriteria.toSpecification())
                .stream().map(e -> {
                    var res = mappingHelper.map(e, FresherDto.class);
                    res.setProfileDto(mappingHelper.map(e.getProfile(), ProfileDto.class));
                    if (e.getCenter() != null)
                        res.setCenterDto(mappingHelper.map(e.getCenter(), CenterDto.class));
                    return res;
                }).collect(Collectors.toList());
    }
}
