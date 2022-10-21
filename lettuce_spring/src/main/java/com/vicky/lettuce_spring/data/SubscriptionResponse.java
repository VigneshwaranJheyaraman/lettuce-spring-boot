package com.vicky.lettuce_spring.data;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class SubscriptionResponse<T> implements Serializable{
    int status;
    T data;
    String error;

    public SubscriptionResponse(int status, T data, String error) {
        this.status= status;
        this.data=  data;
        this.error= error!=null && error.length() > 0 ? error : null;
    }
}
