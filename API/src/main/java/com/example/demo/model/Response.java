package com.example.demo.model;

/*
 * Generate an standart response for all request
*/
public class Response <T> {
    
    private int code;
    private String error;
    private String message;
    private T result;
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getResult() {
        return result;
    }
    public void setResult(T result) {
        this.result = result;
    }

    public Response(int code, String error, String message, T result) {
        this.code = code;
        this.error = error;
        this.message = message;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response [code=" + code + ", error=" + error + ", message=" + message + ", result=" + result + "]";
    }

    
}
