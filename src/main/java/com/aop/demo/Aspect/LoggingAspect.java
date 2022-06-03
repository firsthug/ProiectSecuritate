package com.aop.demo.Aspect;

import com.aop.demo.Model.AOPLogs;
import com.aop.demo.Service.AOPLogsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut(value = "execution(* com.aop.demo.Repository.*.save*(..))")
    public void dbSavePointcut(){}

    @Pointcut(value = "execution(* com.aop.demo.Repository.AOPLogsRepository.save(*))")
    public void dbSaveLoggPointcut(){}

    @AfterReturning("dbSavePointcut() && target(bean)")
    public void dbSaveSuccessfully(JoinPoint jp, Object bean){
        logger.info("Database save ended successfully " + correctSignature(bean, jp.getSignature().toString()));
    }


    @AfterThrowing(value = "dbSavePointcut() && !dbSaveLoggPointcut() && target(bean)", throwing = "e")
    public void dbSaveFailExceptSaveLogs(JoinPoint jp, Object bean, Exception e){
        logger.info("Log error in database");
        saveLog(createLog(e, correctSignature(bean, jp.getSignature().toString()),"Database", "Try to save record"));
    }

    @AfterThrowing(value = "dbSaveLoggPointcut() && target(bean) && args(aopLog)", throwing = "e")
    public void dbSaveFailForSaveLogs(AOPLogs aopLog, Object bean, Exception e){
        logger.error("Database save log threw an error: " + e);
        logger.error(String.valueOf(aopLog));
    }


    private String correctSignature (Object bean, String oldSignature){
        Advised advised = (Advised) bean;
        String source = String.valueOf(advised.getProxiedInterfaces()[0]);
        String className = source.substring(source.lastIndexOf('.') + 1).trim();
        return oldSignature + " - " + className;
    }


    @Autowired
    private AOPLogsService aopLogsService;

    public void saveLog(AOPLogs aopLog){
        aopLogsService.save(aopLog);
    }

    public AOPLogs createLog(Exception e, String signature, String exType, String exInput){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        String errMessage = e.getCause()!=null && e.getCause().getCause()!=null ? e.getCause().getCause().toString() : e.getMessage();

        AOPLogs aopLog = new AOPLogs();
        aopLog.setStacktrace(exceptionAsString.substring(0, Math.min(exceptionAsString.length(),4000)));
        aopLog.setMethod(signature.substring(0, Math.min(signature.length(),80)));
        aopLog.setType(exType);
        aopLog.setExceptionName(errMessage.substring(0,Math.min(errMessage.length(),100)));
        aopLog.setInput(exInput);

        return aopLog;
    }
}