package com.example.ravi.payloads.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response<T> {

    private int code;
    private  boolean error;
    private T data;
    private List<T> dataList;
    private String message;

    public Response(int code, boolean error) {
        this.code = code;
        this.error = error;
    }

    public Response(int code, boolean error, List<T> dataList) {
        this.code = code;
        this.error = error;
        this.dataList = dataList;
    }

    public Response(boolean error, int code, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public Response(int code, boolean error, T data) {
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public Response(int code, boolean error, T data, String message) {
        this.code = code;
        this.error = error;
        this.data = data;
        this.message = message;
    }
}

