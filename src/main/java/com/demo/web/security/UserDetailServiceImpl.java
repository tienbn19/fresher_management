package com.demo.web.security;

import com.demo.model.Account;
import com.demo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository
                .findByEmailOrUsername(email, email)
                .map(this::createUserSecurity)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + email + "' is not exist in system"));

    }

    private org.springframework.security.core.userdetails.User createUserSecurity(Account account) {
        Set<GrantedAuthority> securityAuthorities = Collections.singleton(
                new SimpleGrantedAuthority(AuthoritiesConstants.USER));

        return new org.springframework.security.core.userdetails.User(account.getEmail()
                , account.getPassword(), securityAuthorities);

    }

}
