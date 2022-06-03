package com.aop.demo.Service;

import com.aop.demo.Model.AOPCase;
import com.aop.demo.Model.AOPUser;
import com.aop.demo.Repository.AOPCaseRepository;
import com.aop.demo.Repository.AOPUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AOPCaseServiceImpl implements AOPCaseService{

    @Autowired
    APIService apiService;

    @Autowired
    AOPCaseRepository aopCaseRepository;

    @Autowired
    AOPUserRepository aopUserRepository;

    @Override
    public AOPCase save(AOPCase AopCase, String username) throws Exception {
        AOPUser aopUser = aopUserRepository.getAOPUserByUsername(username);
        AopCase.setCaseUser(aopUser);
        AopCase.setCreatedDate(LocalDateTime.now());
        AopCase.setJiraStatus("To Do");

        String jiraCaseId = apiService.sendCaseToJira(AopCase);
        AopCase.setJiraID(jiraCaseId);

        AOPCase aopCase = aopCaseRepository.save(AopCase);
        return aopCase;
    }

    @Override
    public AOPCase getAOPCaseById(int caseId) throws Exception{
        return aopCaseRepository.getAOPCaseByCaseID(caseId);
    }

    @Override
    public List<AOPCase> getAllUserCases(String username) throws Exception{
        AOPUser aopUser = aopUserRepository.getAOPUserByUsername(username);
        return aopCaseRepository.getAOPCasesByUserId(aopUser.getUserID());
    }

    @Override
    public List<AOPCase> updateCasesStatus(List<AOPCase> cases, Map<String, String> keyStatusJiraMap) {
        List<AOPCase> casesToUpdate = new ArrayList<>();
        for(AOPCase c : cases){
            if(!c.getJiraStatus().equals(keyStatusJiraMap.get(c.getJiraID()))){
                c.setJiraStatus(keyStatusJiraMap.get(c.getJiraID()));
                casesToUpdate.add(c);
            }
        }
        return (List<AOPCase>) aopCaseRepository.saveAll(casesToUpdate);
    }

    @Override
    public List<String> extractJiraKeys(List<AOPCase> aopCases) {
        List<String> keys = new ArrayList<>();
        for(AOPCase c : aopCases){
            keys.add(c.getJiraID());
        }
        return keys;
    }

}
