package com.freelancer.RealEstate.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @param <t> the generic type
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    private String status;

    private String message;

    private Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
