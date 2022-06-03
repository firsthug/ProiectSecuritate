package com.aop.demo.Repository;

import com.aop.demo.Model.AOPCase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AOPCaseRepository extends CrudRepository<AOPCase, Long> {

    AOPCase getAOPCaseByCaseID(int Id);

    @Query("select c from AOPCase c where c.caseUser.userID =:id")
    List<AOPCase> getAOPCasesByUserId(@Param("id") int id);
}
