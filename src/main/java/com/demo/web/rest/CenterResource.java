package com.demo.web.rest;

import com.demo.service.CenterService;
import com.demo.web.dto.request.CenterReq;
import com.demo.web.dto.request.FresherInCenterReq;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterResource {
    private final CenterService centerService;

    @GetMapping
    public ResponseEntity<?> getAllCenter() {
        return ResponseUtils.ok(centerService.getAllCenter());
    }

    @PostMapping
    public ResponseEntity<?> createCenter(@RequestBody CenterReq centerReq) {
        centerService.createCenter(centerReq);
        return ResponseUtils.created();
    }

    @PutMapping("/{centerId}")
    public ResponseEntity<?> updateCenter(@RequestBody CenterReq centerReq, @PathVariable String centerId) {
        centerService.updateCenter(centerId, centerReq);
        return ResponseUtils.ok("Updated");
    }

    @DeleteMapping("/{centerId}")
    public ResponseEntity<?> deleteCenter(@PathVariable String centerId) {
        centerService.deleteCenter(centerId);
        return ResponseUtils.ok("Removed");
    }

    @PostMapping("/add-fresher-into-center")
    public ResponseEntity<?> addFresherIntoCenter(@RequestBody FresherInCenterReq fresherInCenterReq) {
        centerService.addFresherIntoCenter(fresherInCenterReq);
        return ResponseUtils.created();
    }
}
