package com.beck.enums;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum UrlsEnum {
    ALLOW_TOKEN_URLS(Arrays.asList("POST/login", "POST/register"));

    private List<String> urls;

    UrlsEnum(List<String> urls) {
        this.urls = urls;
    }
}
