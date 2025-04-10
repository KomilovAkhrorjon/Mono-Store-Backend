package com.qiroldev.monomarket.utils.exception;

import com.qiroldev.monomarket.utils.model.Response;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends Response {
    private String message;
    private Map<String, Object> param;

    public ErrorResponse(ApiException e) {
//        super(e.result);
//        this.message = e.message;
//        this.param = e.param;
    }
}
