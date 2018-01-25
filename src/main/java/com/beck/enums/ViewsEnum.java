package com.beck.enums;

import lombok.Getter;

@Getter
public enum ViewsEnum {

    INDEX_VIEW("/index"),
    ERROR_VIEW("/error");

    private String indexView;

    ViewsEnum(String indexView) {
        this.indexView=indexView;
    }
}
