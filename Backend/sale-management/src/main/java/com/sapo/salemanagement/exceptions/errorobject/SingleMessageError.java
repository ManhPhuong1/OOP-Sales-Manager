package com.sapo.salemanagement.exceptions.errorobject;

import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class SingleMessageError extends ErrorObject{
    private String message;

    public SingleMessageError(int statusCode, String message, Date timestamp) {
        super(statusCode, timestamp);
        this.message = message;
    }
}
