package com.aop.demo.Model;

public class AOPContractDTO {

    private AOPContract aopContract;

    private AOPConsumptionPoint aopConsumptionPoint;

    public AOPContract getAopContract() {
        return aopContract;
    }

    public void setAopContract(AOPContract aopContract) {
        this.aopContract = aopContract;
    }

    public void setAopConsumptionPoint(AOPConsumptionPoint aopConsumptionPoint) {
        this.aopConsumptionPoint = aopConsumptionPoint;
    }

    public AOPConsumptionPoint getAopConsumptionPoint() {
        return aopConsumptionPoint;
    }

    public AOPContractDTO(AOPContract aopContract, AOPConsumptionPoint aopConsumptionPoint) {
        this.aopContract = aopContract;
        this.aopConsumptionPoint = aopConsumptionPoint;
    }

    public AOPContractDTO() {
    }
}
