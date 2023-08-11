package com.demo.service.impl;

import com.demo.model.Account;
import com.demo.repository.AccountRepository;
import com.demo.service.AuthService;
import com.demo.web.dto.request.LoginRequest;
import com.demo.web.dto.request.SignupRequest;
import com.demo.web.dto.response.JwtResponse;
import com.demo.web.exception.ServiceException;
import com.demo.web.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse authenticateAccount(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtils.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return new JwtResponse(jwtToken, "Bearer", userDetails.getUsername());

        } catch (AuthenticationException authenticationException) {
            throw new ServiceException("Email or password is invalid", "err.authorize.unauthorized");
        }

    }

    @Override
    public void createAccount(SignupRequest signupRequest) {
        if (accountRepository.existsByEmailOrUsername(signupRequest.getEmail(), signupRequest.getUsername()))
            throw new ServiceException("Email or username is existed in system", "err.api.email-username-is-existed");

        Account account = new Account();
        account.setUsername(signupRequest.getUsername());
        account.setEmail(signupRequest.getEmail());
        account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        accountRepository.save(account);
    }
}
