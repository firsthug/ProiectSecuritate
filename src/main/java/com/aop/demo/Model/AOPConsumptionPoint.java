package com.aop.demo.Model;

import javax.persistence.*;

@Entity(name = "AOPCONSUMPTIONPOINT")
@Table(schema="ADMIN1")
public class AOPConsumptionPoint {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="SEQUENCE1")
    @SequenceGenerator(name="SEQUENCE1", sequenceName="ADMIN1.SEQUENCE1", allocationSize=1)
    private Integer CPID;

    private String address;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private AOPUser userID;

    public AOPConsumptionPoint() {
    }

    public AOPConsumptionPoint(Integer CPID, String address, AOPUser userID) {
        this.CPID = CPID;
        this.address = address;
        this.userID = userID;
    }

    public Integer getCPID() {
        return CPID;
    }

    public void setCPID(Integer CPID) {
        this.CPID = CPID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AOPUser getUserID() {
        return userID;
    }

    public void setUserID(AOPUser userID) {
        this.userID = userID;
    }
}
