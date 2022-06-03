package com.aop.demo.Aspect;

import com.aop.demo.Model.AOPComment;
import com.aop.demo.Service.APIService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Aspect
@Component
public class RetryAspect {

    final int MAX_RETRIES = 3;

    @Autowired
    private LoggingAspect loggingAspect;

    //nu se poate folosi un singut poitcut pentru ca nu se poate face diferentierea intre
    //sendCaseToJira si getCasesByIds doar pe baza tipului parametrului (e perceput ca object)
    @Pointcut(value = "execution(String com.aop.demo.Service.APIService.sendCaseToJira(*))")
    public void sendCaseToJiraPointcut(){}

    @Pointcut(value = "execution(* com.aop.demo.Service.APIService.getCasesByIds(*))")
    public void getCasesByIdsPointcut(){}

    @Pointcut(value = "execution(* com.aop.demo.Service.APIService.getCommentsByCaseKey(String))")
    public void getCommentsByCaseKeyPointcut(){}


    @Around("sendCaseToJiraPointcut()")
    public String retrySendCaseToJira(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        int retry = 0;
        Object[] args = proceedingJoinPoint.getArgs();
        while(true){
            try{
                //if(retry == 2){
                //aopCase.setSubject("abc");
                //}
                return proceedingJoinPoint.proceed(args).toString();
            } catch(Throwable ex) {
                System.out.println("Error encountered SendCaseToJira");
                if(++retry > MAX_RETRIES){
                    System.out.println("Can not execute successfully SendCaseToJira");
                    System.out.println("Need to log exception error on SendCaseToJira");
                    try {
                        loggingAspect.saveLog(loggingAspect.createLog(new Exception(ex), String.valueOf(proceedingJoinPoint.getSignature()), "Callout", APIService.POST_CASE_API));
                    }catch(Exception e){};
                    throw ex;
                }
                System.out.println("\tRetrying SendCaseToJira");
            }
        }
    }

    @Around("getCasesByIdsPointcut()")
    public Map<String, String> retryGetCasesByIdsPointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        int retry = 0;
        Object[] args = proceedingJoinPoint.getArgs();
        while(true){
            try{
                return (Map<String, String>) proceedingJoinPoint.proceed(args);
            } catch(Throwable ex) {
                System.out.println("Error encountered GetCasesByIds");
                if(++retry > MAX_RETRIES){
                    System.out.println("Can not execute successfully GetCasesByIds");
                    System.out.println("Need to log exception error on GetCasesByIds");
                    try {
                        loggingAspect.saveLog(loggingAspect.createLog(new Exception(ex), String.valueOf(proceedingJoinPoint.getSignature()), "Callout", APIService.SEARCH_CASES_API));
                    }catch(Exception e){};
                    throw ex;
                }
                System.out.println("\tRetrying GetCasesByIds");
            }
        }
    }


    @Around("getCommentsByCaseKeyPointcut()")
    public List<AOPComment> retryGetCommentsByCaseKeyPointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        int retry = 0;
        Object[] args = proceedingJoinPoint.getArgs();
        while(true){
            try{
                return (List<AOPComment>) proceedingJoinPoint.proceed(args);
            } catch(Throwable ex) {
                System.out.println("Error encountered getCommentsByCaseKeyPointcut");
                if(++retry > MAX_RETRIES){
                    System.out.println("Can not execute successfully getCommentsByCaseKeyPointcut");
                    System.out.println("Need to log exception error on getCommentsByCaseKeyPointcut");
                    try {
                        loggingAspect.saveLog(loggingAspect.createLog(new Exception(ex), String.valueOf(proceedingJoinPoint.getSignature()), "Callout", APIService.CASE_COMMENTS_API));
                    }catch(Exception e){};
                    throw ex;
                }
                System.out.println("\tRetrying getCommentsByCaseKeyPointcut");
            }
        }
    }
}