package com.aop.demo.Model;


import javax.persistence.*;

@Entity
@Table(schema="ADMIN1")
public class AOPLogs {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="ADMIN1.SEQUENCE1", allocationSize=1)
    private Integer logID;

    private String stacktrace;

    private String method;

    private String type;

    @Column(name="exceptionname")
    private String exceptionName;

    private String input;

    public Integer getLogID() {
        return logID;
    }

    public void setLogID(Integer logID) {
        this.logID = logID;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public AOPLogs(Integer logID, String stacktrace, String method, String type, String exceptionName, String input) {
        this.logID = logID;
        this.stacktrace = stacktrace;
        this.method = method;
        this.type = type;
        this.exceptionName = exceptionName;
        this.input = input;
    }

    public AOPLogs()
    {}

    public AOPLogs(Integer logID) {
        this.logID = logID;
    }

    @Override
    public String toString() {
        return "AOPLogs{" +
                "logID=" + logID +
                ", stacktrace='" + stacktrace + '\'' +
                ", method='" + method + '\'' +
                ", type='" + type + '\'' +
                ", exceptionName='" + exceptionName + '\'' +
                ", input='" + input + '\'' +
                '}';
    }
}
