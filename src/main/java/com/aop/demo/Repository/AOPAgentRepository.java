package com.aop.demo.Repository;

import com.aop.demo.Model.AOPAgent;
import com.aop.demo.Model.AOPCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AOPAgentRepository extends JpaRepository<AOPAgent, Integer> {
}
