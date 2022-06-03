package com.aop.demo.Repository;


import com.aop.demo.Model.AOPLogs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AOPLogsRepository extends CrudRepository<AOPLogs, Long> {
}
