package com.aop.demo.Model;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="ADMIN1.SEQUENCE1", allocationSize=1)
    private Integer feedbackNumber;

    private String message;

    @Column(name="feedback_date", columnDefinition="DATE")
    private LocalDateTime feedbackDate;

    private Integer rating;

    private String agentID;

    private Integer userID;

    public Feedback() {
    }

    public Integer getFeedbackNumber() {
        return feedbackNumber;
    }

    public void setFeedbackNumber(Integer feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDateTime feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getAgentID() {
        return agentID;
    }

    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Feedback(Integer feedbackNumber, String message, LocalDateTime feedbackDate, Integer rating, String agentID, Integer userID) {
        this.feedbackNumber = feedbackNumber;
        this.message = message;
        this.feedbackDate = feedbackDate;
        this.rating = rating;
        this.agentID = agentID;
        this.userID = userID;
    }
}
