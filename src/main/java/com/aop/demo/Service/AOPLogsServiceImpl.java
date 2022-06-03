package com.aop.demo.Service;

import com.aop.demo.Model.AOPLogs;
import com.aop.demo.Repository.AOPLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AOPLogsServiceImpl implements AOPLogsService{

    @Autowired
    AOPLogsRepository aopLogsRepository;

    @Override
    public AOPLogs save(AOPLogs aopLogs) {
        AOPLogs aopLogs1 = aopLogsRepository.save(aopLogs);
        return aopLogs1;
    }
}
