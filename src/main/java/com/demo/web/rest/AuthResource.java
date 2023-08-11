package com.demo.web.rest;

import com.demo.service.AuthService;
import com.demo.web.dto.request.LoginRequest;
import com.demo.web.dto.request.SignupRequest;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response> authenticateAccount(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseUtils.ok(authService.authenticateAccount(loginRequest));
    }

    @PostMapping("/create-account")
    public ResponseEntity<Response> createAccount(@Valid @RequestBody SignupRequest signupRequest) {
        authService.createAccount(signupRequest);
        return ResponseUtils.created();
    }

}