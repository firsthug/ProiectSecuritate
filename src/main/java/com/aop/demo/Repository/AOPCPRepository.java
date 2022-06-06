package com.aop.demo.Repository;

import com.aop.demo.Model.AOPConsumptionPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AOPCPRepository extends JpaRepository<AOPConsumptionPoint, Integer> {
}
