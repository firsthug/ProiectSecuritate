package com.aop.demo.Controller;

import com.aop.demo.Model.AOPCase;
import com.aop.demo.Model.AOPUser;
import com.aop.demo.Service.AOPCaseService;
import com.aop.demo.Service.AOPCaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Controller
@RequestMapping("/contact")
public class CaseController {

    @Autowired
    AOPCaseService aopCaseService;

    @GetMapping({"/form"})
    public String newCase(Model model) {
        model.addAttribute("aopCase", new AOPCase());
        return "contact";
    }

    @PostMapping({"/newCase"})
    public String submitNewCase(@Valid AOPCase aopCase) throws Exception {
        AOPCase newCase = aopCaseService.save(aopCase, SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:case/" +newCase.getCaseID();
    }

    @GetMapping({"/myCases"})
    public String getAllUserCases(Model model) throws Exception{
        model.addAttribute("userCases", aopCaseService.getAllUserCases(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "myCases";
    }

    @RequestMapping( "/case/{id}")
    public String getCaseById(@PathVariable Integer id, Model model) throws Exception {
        AOPCase myCase = aopCaseService.getAOPCaseById(id);
        model.addAttribute("case", myCase);
        return "caseView";
    }


}
