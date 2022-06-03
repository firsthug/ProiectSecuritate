package com.aop.demo.Aspect;

import com.aop.demo.Model.AOPCase;
import com.aop.demo.Service.AOPCaseService;
import com.aop.demo.Service.APIService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class UpdateUserCasesAspect {

    @Autowired
    private AOPCaseService aopCaseService;

    @Autowired
    private APIService apiService;

    @Autowired
    private SpringCachingAspect springCachingAspect;


    @Pointcut(value = "execution(* com.aop.demo.Service.AOPCaseService.getAllUserCases(*))")
    public void dbGetUserCasesPointcut(){}

    @Around("dbGetUserCasesPointcut()")
    public List<AOPCase> updateUserCasesWithJiraStatuses(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Get status for cases aspect");
        Object[] args = proceedingJoinPoint.getArgs();
        List<AOPCase> cases = (List<AOPCase>) proceedingJoinPoint.proceed(args);
        Map<Integer, AOPCase> casesMap = new HashMap<>();
        for(AOPCase thisCase : cases)
        {
            casesMap.put(thisCase.getCaseID(), thisCase);
        }

        try {
            List<String> jiraIdsForCases = aopCaseService.extractJiraKeys(cases);
            //jiraIdsForCases.clear();
            //jiraIdsForCases.add("1234de");//to throw error
            Map<String,String> keyStatusMap = apiService.getCasesByIds(jiraIdsForCases);
            List<AOPCase> updatedCases = aopCaseService.updateCasesStatus(cases, keyStatusMap);
            for(AOPCase thisCase : updatedCases)
            {
                casesMap.put(thisCase.getCaseID(), thisCase);
            }
        } catch (Exception e) {
            System.out.println("exception is:"+e);
        }

        springCachingAspect.setCache(args[0].toString()+"Cases", new ArrayList<>(casesMap.values()));
        return new ArrayList<>(casesMap.values());
    }
}