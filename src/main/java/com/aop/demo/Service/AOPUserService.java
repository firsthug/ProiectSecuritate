package com.aop.demo.Service;

import com.aop.demo.Model.AOPAgent;
import com.aop.demo.Model.AOPConsumer;
import com.aop.demo.Model.AOPUser;
import com.aop.demo.Repository.AOPAgentRepository;
import com.aop.demo.Repository.AOPConsumerRepository;
import com.aop.demo.Repository.AOPUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AOPUserService {

    @Autowired
    private AOPUserRepository aopUserRepository;

    @Autowired
    private AOPConsumerRepository aopConsumerRepository;

    @Autowired
    private AOPAgentRepository aopAgentRepository;

    public void saveConsumer(AOPConsumer consumer)
    {
        aopConsumerRepository.save(consumer);
    }

    public void saveAgent(AOPAgent agent)
    {
        aopAgentRepository.save(agent);
    }

    public void saveUSER(AOPUser user) {
        aopUserRepository.save(user);
    }
}
