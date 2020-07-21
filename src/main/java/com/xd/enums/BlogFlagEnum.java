package com.xd.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlogFlagEnum {
    DIY(1,"原创"),
    COPY(2,"转载"),
    TRANSLATE(3,"翻译");
    private Integer value;
    private String Type;
}
