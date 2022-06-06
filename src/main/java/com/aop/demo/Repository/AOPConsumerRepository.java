package com.aop.demo.Repository;

import com.aop.demo.Model.AOPConsumer;
import com.aop.demo.Model.AOPUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AOPConsumerRepository extends JpaRepository<AOPConsumer, Integer> {

    AOPConsumer getAOPConsumerByUsername(String username);
    AOPConsumer getAOPConsumerByUserID(Integer userID);

    List<AOPConsumer> findAllByType(String type);
}
