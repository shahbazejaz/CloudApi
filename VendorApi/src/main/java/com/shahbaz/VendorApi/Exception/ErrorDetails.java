package com.shahbaz.VendorApi.Exception;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String details;
    private String message;

    public ErrorDetails(String details, String message,Date timestamp) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
