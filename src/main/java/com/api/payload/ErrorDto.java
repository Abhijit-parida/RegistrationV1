package com.api.payload;

import java.util.Date;

public class ErrorDto {
    private String message;
    private Date date;
    private String uri;

    public ErrorDto(String message, Date date, String uri) {
        this.message = message;
        this.date = date;
        this.uri = uri;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getUri() {
        return uri;
    }
}
