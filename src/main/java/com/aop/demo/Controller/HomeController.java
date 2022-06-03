package com.aop.demo.Controller;


import com.aop.demo.Exception.NotFoundException;
import com.aop.demo.Model.AOPUser;
import com.aop.demo.Service.AOPUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    AOPUserService aopUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping({"", "/", "/welcome"})
    public String getWelcomePage() {
        return "welcome";
    }

    @GetMapping({"/signUp", "/signup"})
    public String newCustomer(Model model) {
        model.addAttribute("aopUser", new AOPUser());
        return "signUp";
    }

    @PostMapping({"/signUp", "/signup"})
    public String saveOrUpdate(@Valid AOPUser aopUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        aopUser.setPassword(passwordEncoder.encode(aopUser.getPassword()));
        aopUser.setUsername(aopUser.getEmail());
        aopUserService.save(aopUser);
        return "login";
    }


    @GetMapping("/login")
    public String getUserLoginPage() {
        if (isAuthenticated() == false) {
            return "login";
        }
        return "welcome";
    }

    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken);
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("errorMessage","Invalid username or password");
        return "login-error";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }

//    @RequestMapping(value = "/**/{spring:\\w+}")
//    public String unmatchedRequests()
//    {
//        throw new NotFoundException("Nothing here!");
//    }

}
