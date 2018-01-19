package com.beck.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ResponseDTO {
    @JSONField(ordinal = 3)
    public Integer statusCode;

    @JSONField(ordinal = 1)
    public String requestUrl;

    @JSONField(ordinal = 2)
    public String method;

    @JSONField(ordinal = 4)
    public Object responseArgs;


}
