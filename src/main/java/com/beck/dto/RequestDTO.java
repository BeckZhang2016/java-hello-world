package com.beck.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class RequestDTO {
    @JSONField(ordinal = 1)
    public String requestUri;

    @JSONField(ordinal = 2)
    public String method;

    @JSONField(ordinal = 3)
    public Object[] requestArgs;

}
