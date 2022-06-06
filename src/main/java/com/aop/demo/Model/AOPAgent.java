package com.aop.demo.Model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("agent")
@Table(schema = "ADMIN1")
public class AOPAgent extends AOPUser{

    @Column(name="hire_date", columnDefinition="DATE")
    private LocalDateTime hireDate;

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }
}
