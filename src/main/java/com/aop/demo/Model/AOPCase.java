package com.aop.demo.Model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AOPCase {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="SEQUENCE1", allocationSize=1)
    private Integer caseID;

    private String subject;

    private String priority;

    private String type;

    private String category;

    private String description;

    private String jiraID;

    @Column(name="jirastatus")
    private String jiraStatus;

    @Column(name="createddate", columnDefinition="DATE")
    private LocalDateTime createdDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private AOPUser caseUser;

    public Integer getCaseID() {
        return caseID;
    }

    public void setCaseID(Integer caseID) {
        this.caseID = caseID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJiraID() {
        return jiraID;
    }

    public void setJiraID(String jiraID) {
        this.jiraID = jiraID;
    }

    public String getJiraStatus() {
        return jiraStatus;
    }

    public void setJiraStatus(String jiraStatus) {
        this.jiraStatus = jiraStatus;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public AOPUser getCaseUser() {
        return caseUser;
    }

    public void setCaseUser(AOPUser caseUser) {
        this.caseUser = caseUser;
    }

    public AOPCase(Integer caseID, String subject, String priority, String type, String category, String description, String jiraID, String jiraStatus, LocalDateTime createdDate, AOPUser caseUser) {
        this.caseID = caseID;
        this.subject = subject;
        this.priority = priority;
        this.type = type;
        this.category = category;
        this.description = description;
        this.jiraID = jiraID;
        this.jiraStatus = jiraStatus;
        this.createdDate = createdDate;
        this.caseUser = caseUser;
    }

    public AOPCase(){};

    public AOPCase(Integer caseID) {
        this.caseID = caseID;
    }

    @Override
    public String toString() {
        return "AOPCase{" +
                "caseID=" + caseID +
                ", subject='" + subject + '\'' +
                ", priority='" + priority + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", jiraID='" + jiraID + '\'' +
                ", jiraStatus='" + jiraStatus + '\'' +
                ", createdDate=" + createdDate +
                ", caseUser=" + caseUser +
                ", comments=" + comments +
                '}';
    }

    @Transient
    public List<AOPComment> comments;

    public List<AOPComment> getComments() {
        return comments;
    }

    public void setComments(List<AOPComment> comments) {
        this.comments = comments;
    }
}
