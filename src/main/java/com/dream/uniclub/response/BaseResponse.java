package com.dream.uniclub.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BaseResponse {
    private int statusCode = 200;
    private String message;
    private boolean data;
}
