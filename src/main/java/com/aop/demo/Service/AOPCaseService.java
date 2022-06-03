package com.aop.demo.Service;

import com.aop.demo.Model.AOPCase;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AOPCaseService {

    AOPCase save(AOPCase AopCase, String username) throws Exception;

    AOPCase getAOPCaseById(int caseId) throws Exception;

    List<AOPCase> getAllUserCases(String username) throws Exception;

    List<AOPCase> updateCasesStatus (List<AOPCase> cases, Map<String, String> keyStatusJiraMap);

    List<String> extractJiraKeys (List<AOPCase> aopCases);
}
