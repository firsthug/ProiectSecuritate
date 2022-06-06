package com.aop.demo.Service;

import com.aop.demo.Model.*;
import com.aop.demo.Repository.AOPCPRepository;
import com.aop.demo.Repository.AOPConsumerRepository;
import com.aop.demo.Repository.AOPContractRepository;
import com.aop.demo.Repository.AOPUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AOPContractService {

    @Autowired
    private AOPContractRepository aopContractRepository;

    @Autowired
    private AOPCPRepository aopCPRepository;

    @Autowired
    AOPUserRepository aopUserRepository;

    @Autowired
    AOPConsumerRepository aopConsumerRepository;

    public List<AOPContract> getAllUserContracts(String username) throws Exception{
        AOPConsumer aopConsumer = aopConsumerRepository.getAOPConsumerByUsername(username);
        return aopContractRepository.getAOPContractByUserIDUserID(aopConsumer.getUserID());
    }

    public void save(AOPContract aopContract, AOPConsumptionPoint aopCP) throws Exception{
        AOPConsumer aopConsumer = aopConsumerRepository.getAOPConsumerByUserID(aopContract.getUserID().getUserID());
        aopCP.setUserID(aopConsumer);
        aopContract.setUserID(aopConsumer);
        aopContract.setStatus("pending");
        try{
            AOPConsumptionPoint newConsumptionPoint = aopCPRepository.save(aopCP);
            aopContract.setConsumptionPoint(newConsumptionPoint);
            aopContractRepository.save(aopContract);
        }
        catch (Exception e)
        {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            System.out.println(e.getLocalizedMessage());
        }


    }

    public List<AOPConsumer> getAllConsumers() {
        return aopConsumerRepository.findAllByType("consumer");
    }
}
