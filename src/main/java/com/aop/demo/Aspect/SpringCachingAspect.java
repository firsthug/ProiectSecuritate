package com.aop.demo.Aspect;

import com.aop.demo.Model.AOPCase;
import com.aop.demo.Model.AOPUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class SpringCachingAspect {

    private final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    public void setCache(String key, Object value) {
        cache.put(key, value);
    }

    @Around("execution(* com.aop.demo.Repository.AOPUserRepository.getAOPUserByUsername(String)) && args(username)")
    public Object getUser(ProceedingJoinPoint proceedingJoinPoint, String username) throws Throwable {
        Object existingUser = cache.get(username);
        if (existingUser != null) {
            System.out.println("User Found In Cache " + existingUser);
            return (AOPUser) existingUser;
        }
        Object[] thisUsername = {username};
        Object newUser = proceedingJoinPoint.proceed(thisUsername);
        System.out.println("User Not Found In Cache " + newUser);
        cache.put(username, (AOPUser) newUser);
        return (AOPUser) newUser;
    }

    @Around("execution(* com.aop.demo.Repository.AOPCaseRepository.getAOPCaseByCaseID(int)) && args(Id)")
    public Object getCase(ProceedingJoinPoint proceedingJoinPoint, int Id) throws Throwable {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Object> existingUserCases = new ArrayList();
        if (cache.containsKey(username + "Cases")) {
            existingUserCases.addAll((List<Object>) cache.get(username + "Cases"));
            if (existingUserCases != null && !existingUserCases.isEmpty()) {
                for (Object existingCase : existingUserCases) {
                    AOPCase userCase = (AOPCase) existingCase;
                    if (userCase.getCaseID() == Id) {
                        System.out.println("Case Found In Cache " + userCase.getCaseID());
                        return userCase;
                    }
                }
            }
        }

        Object[] thisId = {Id};
        Object newCase = proceedingJoinPoint.proceed(thisId);
        System.out.println("Case Not Found In Cache " + Id);
        existingUserCases.add(newCase);
        cache.put(username + "Cases", existingUserCases);
        return (AOPCase) newCase;
    }
}