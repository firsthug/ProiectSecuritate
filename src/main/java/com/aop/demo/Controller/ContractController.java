package com.aop.demo.Controller;

import com.aop.demo.Model.*;
import com.aop.demo.Service.AOPCaseService;
import com.aop.demo.Service.AOPContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/contract")
public class ContractController{

    @Autowired
    AOPContractService aopContractService;

    @GetMapping({"/myContracts"})
    public String getAllUserCases(Model model) throws Exception{
        model.addAttribute("userContracts", aopContractService.getAllUserContracts(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "contracts";
    }

    @GetMapping({"/new"})
    public String newContract(Model model) {
        AOPContractDTO aopContractDTO = new AOPContractDTO(new AOPContract(),new AOPConsumptionPoint());
        model.addAttribute("aopContractDTO", aopContractDTO);
        List<AOPConsumer> consumers = aopContractService.getAllConsumers();
        model.addAttribute("consumers", consumers);
        return "newContract";
    }

    @PostMapping(value = "/new" )
    public String submitNewContract(AOPContractDTO aopContractDTO) throws Exception {
        aopContractService.save(aopContractDTO.getAopContract(),aopContractDTO.getAopConsumptionPoint());
        return "redirect:/welcome";
    }


}
