package com.visionary.crofting.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private ResponseCode responseCode;
    private String responseMessage;
    private T data;

    public enum ResponseCode{
        SUCCESS,
        VALIDATION_ERROR,
        NOT_ALLOWED,
        INVALID_CREDENTIALS,
        INVALID_TOKEN,
        ERROR_TECHNIQUE,
        NOT_EXIST
    }
}
