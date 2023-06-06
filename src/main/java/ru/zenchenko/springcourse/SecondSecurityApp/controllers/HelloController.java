package ru.zenchenko.springcourse.SecondSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.zenchenko.springcourse.SecondSecurityApp.service.AdminService;
import ru.zenchenko.springcourse.SecondSecurityApp.security.PersonDetails;
import ru.zenchenko.springcourse.SecondSecurityApp.service.DirectoryService;

@Controller
public class HelloController {

    private final AdminService adminService;
    private final DirectoryService directoryService;
    @Autowired
    public HelloController(AdminService adminService, DirectoryService directoryService) {
        this.adminService = adminService;
        this.directoryService = directoryService;
    }

    @GetMapping("/hello")
    public String hello() {
        directoryService.updateFiles();
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String info() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage(){
        adminService.doAdminStuff();
        return "admin";
    }
}
