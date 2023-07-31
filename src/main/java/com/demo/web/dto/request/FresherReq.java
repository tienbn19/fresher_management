package com.demo.web.dto.request;

import lombok.Data;

@Data
public class FresherReq {
    private ProfileReq profileReq;
    private String program;
    private String email;
}
