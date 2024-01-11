package com.bookrental.bookrental.controller;

import com.bookrental.bookrental.config.CustomMessageSource;
import com.bookrental.bookrental.enums.ResponseStatus;
import com.bookrental.bookrental.generic.GlobalApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBaseController {

    @Autowired
    protected CustomMessageSource customMessageSource;
    private static final ResponseStatus API_SUCCESS_STATUS = ResponseStatus.SUCCESS;
    private static final ResponseStatus API_FAIL_STATUS = ResponseStatus.FAIL;

    protected String module;

    protected void setModule(String module) {
        this.module = module;
    }

    protected String getModule() {
        return module;
    }


    protected GlobalApiResponse successResponse(String message, Object data) {
        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        globalApiResponse.setResponseStatus(API_SUCCESS_STATUS);
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(data);
        return globalApiResponse;
    }


    protected GlobalApiResponse errorResponse(String message, Object data) {
        GlobalApiResponse globalApiResponse = new GlobalApiResponse();
        globalApiResponse.setResponseStatus(API_FAIL_STATUS);
        globalApiResponse.setMessage(message);
        globalApiResponse.setData(data);
        return globalApiResponse;
    }


}
