package com.demo.web.rest;

import com.demo.service.FresherService;
import com.demo.web.dto.request.FresherCriteria;
import com.demo.web.dto.request.FresherReq;
import com.demo.web.dto.request.PointReq;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/freshers")
@RequiredArgsConstructor
public class FresherResource {
    private final FresherService fresherService;

    @GetMapping
    public ResponseEntity<?> getAllFresher() {
        return ResponseUtils.ok(fresherService.getAllFresher());
    }

    @PostMapping
    public ResponseEntity<?> createFresher(@RequestBody FresherReq fresherReq) {
        fresherService.createFresher(fresherReq);
        return ResponseUtils.created();
    }

    @PutMapping("/{fresherId}")
    public ResponseEntity<?> updateFresherInfo(@RequestBody FresherReq fresherReq, @PathVariable String fresherId) {
        fresherService.updateFresherInfo(fresherId, fresherReq);
        return ResponseUtils.ok("Updated");
    }

    @DeleteMapping("/{fresherId}")
    public ResponseEntity<?> deleteFresher(@PathVariable String fresherId) {
        fresherService.deleteFresher(fresherId);
        return ResponseUtils.ok("Removed");
    }

    @PostMapping("/calculate-point/{fresherId}")
    public ResponseEntity<?> calculatePoint(@RequestBody @Valid PointReq pointReq, @PathVariable String fresherId) {
        return ResponseUtils.ok(fresherService.calculatePoint(fresherId, pointReq));
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchFresher(@RequestBody FresherCriteria fresherCriteria) {
        return ResponseUtils.ok(fresherService.searchFresher(fresherCriteria));
    }
}
