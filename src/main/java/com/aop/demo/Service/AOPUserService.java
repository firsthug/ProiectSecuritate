package com.aop.demo.Service;

import com.aop.demo.Model.AOPUser;
import com.aop.demo.Repository.AOPUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AOPUserService {

    @Autowired
    private AOPUserRepository aopUserRepository;


    public void save(AOPUser user) {
        aopUserRepository.save(user);

    }
}
