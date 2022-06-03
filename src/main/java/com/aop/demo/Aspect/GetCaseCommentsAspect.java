package com.aop.demo.Aspect;

import com.aop.demo.Model.AOPCase;
import com.aop.demo.Service.APIService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GetCaseCommentsAspect {

    @Autowired
    private APIService apiService;

    @Pointcut(value = "execution(* com.aop.demo.Service.AOPCaseService.getAOPCaseById(int))")
    public void getCasePointcut(){}

    @Around("getCasePointcut()")
    public AOPCase completeCaseWithJiraComments(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println("Get comments for case aspect");
        AOPCase aopCase = (AOPCase) proceedingJoinPoint.proceed(args);

        String jiraId = aopCase.getJiraID();
        if(jiraId != null) {
            try {
                //aopCase.setComments(apiService.getCommentsByCaseKey(null));//to throw error
                aopCase.setComments(apiService.getCommentsByCaseKey(jiraId));
            } catch (Exception e) {}
        }
        return aopCase;
    }
}
