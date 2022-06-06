package com.aop.demo.Model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="AOPCONSUMER")
@DiscriminatorValue("consumer")
@Table(schema="ADMIN1")
public class AOPConsumer extends AOPUser {

    private String CNP;

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }
}
