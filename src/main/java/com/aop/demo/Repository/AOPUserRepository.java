package com.aop.demo.Repository;

import com.aop.demo.Model.AOPUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface AOPUserRepository extends JpaRepository<AOPUser, Integer> {
    AOPUser getAOPUserByUsername(String username);

//    private final JdbcTemplate jdbcTemplate;
//
//    public AOPUserRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public void save(AOPUser user) {
//        jdbcTemplate.update("INSERT INTO AOPUSER VALUES(?,?,?,?,?)",
//                user.getUserID(), user.getEmail(), user.getPassword(), user.getFullname(), user.getPhone());
//    }


}
