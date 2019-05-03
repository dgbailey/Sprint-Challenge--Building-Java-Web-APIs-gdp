package com.lambdaschool.sprint.gdp.gdp.Models;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//




import com.lambdaschool.sprint.gdp.gdp.Exception.ValidationError;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {
    private String title;
    private int status;
    private String detail;
    private String timestamp;
    private String developerMessage;
    private Map<String, List<ValidationError>> errors = new HashMap();

    public ErrorDetail() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = (new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS")).format(new Date(timestamp));
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Map<String, List<ValidationError>> getErrors() {
        return this.errors;
    }

    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }
}
