package com.demo.service.impl;

import com.demo.model.Center;
import com.demo.repository.CenterRepository;
import com.demo.repository.FresherRepository;
import com.demo.service.CenterService;
import com.demo.service.utils.MappingHelper;
import com.demo.web.dto.CenterDto;
import com.demo.web.dto.request.CenterReq;
import com.demo.web.dto.request.FresherInCenterReq;
import com.demo.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {
    private final CenterRepository centerRepository;
    private final FresherRepository fresherRepository;
    private final MappingHelper mappingHelper;

    @Override
    public List<CenterDto> getAllCenter() {
        return centerRepository.findAll()
                .stream().map(e -> mappingHelper.map(e, CenterDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createCenter(CenterReq centerReq) {
        var center = mappingHelper.map(centerReq, Center.class);
        centerRepository.save(center);
    }

    @Override
    public void updateCenter(String centerId, CenterReq centerReq) {
        centerRepository.findById(centerId)
                .map(e -> {
                    mappingHelper.mapIfSourceNotNullAndStringNotBlank(centerReq, e);
                    centerRepository.save(e);
                    return e;
                })
                .orElseThrow(() -> new EntityNotFoundException(Center.class.getName(), centerId));
    }

    @Override
    public void deleteCenter(String centerId) {
        fresherRepository.findByCenter_Id(centerId)
                .forEach(fresherRepository::delete);
        centerRepository.deleteById(centerId);
    }

    @Override
    public void addFresherIntoCenter(FresherInCenterReq fresherInCenterReq) {
        centerRepository.findById(fresherInCenterReq.getCenterId())
                .map(e -> {
                    fresherRepository.findById(fresherInCenterReq.getFresherId())
                            .map(fresher -> {
                                fresher.setCenter(e);
                                fresherRepository.save(fresher);
                                return fresher;
                            })
                            .orElseThrow(() -> new EntityNotFoundException(Center.class.getName()
                                    , fresherInCenterReq.getFresherId()));
                    return e;
                })
                .orElseThrow(() -> new EntityNotFoundException(Center.class.getName(), fresherInCenterReq.getCenterId()));
    }
}
