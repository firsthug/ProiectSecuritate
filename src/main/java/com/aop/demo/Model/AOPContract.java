package com.aop.demo.Model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema="ADMIN1")
public class AOPContract {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="ADMIN1.SEQUENCE1", allocationSize=1)
    private Integer contractID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private AOPUser userID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CPID", referencedColumnName = "CPID")
    private AOPConsumptionPoint consumptionPoint;

    @Column(name="start_date", columnDefinition="DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @Column(name="end_date", columnDefinition="DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    private String status;

    private String category;

    public AOPContract() {
    }

    public AOPContract(Integer contractID, AOPUser userID, AOPConsumptionPoint consumptionPoint, LocalDateTime startDate, LocalDateTime endDate, String status, String category) {
        this.contractID = contractID;
        this.userID = userID;
        this.consumptionPoint = consumptionPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.category = category;
    }

    public Integer getContractID() {
        return contractID;
    }

    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public AOPUser getUserID() {
        return userID;
    }

    public void setUserID(AOPUser userID) {
        this.userID = userID;
    }

    public AOPConsumptionPoint getConsumptionPoint() {
        return consumptionPoint;
    }

    public void setConsumptionPoint(AOPConsumptionPoint consumptionPoint) {
        this.consumptionPoint = consumptionPoint;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
