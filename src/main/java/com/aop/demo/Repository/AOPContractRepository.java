package com.aop.demo.Repository;

import com.aop.demo.Model.AOPContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AOPContractRepository extends JpaRepository<AOPContract, Integer> {


    List<AOPContract> getAOPContractByUserIDUserID(Integer userID);
}
