package com.demo.web.rest;

import com.demo.service.StatisticalService;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistical")
@RequiredArgsConstructor
public class StatisticalResource {
    private final StatisticalService statisticalService;

    @GetMapping("/fresher-of-center")
    public ResponseEntity<?> fresherOfCenter() {
        return ResponseUtils.ok(statisticalService.fresherOfCenter());
    }

    @GetMapping("/fresher-by-scores")
    public ResponseEntity<?> fresherByScore() {
        return ResponseUtils.ok(statisticalService.fresherByScore());
    }
}
