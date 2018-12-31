package com.mad.projects.finance.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mad.projects.finance.domain.vo.BaseError;

public class BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BaseError error;

    private String description;

    public BaseResponse() {
    }

    public BaseError getError() {
        return error;
    }

    public void setError(BaseError error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
