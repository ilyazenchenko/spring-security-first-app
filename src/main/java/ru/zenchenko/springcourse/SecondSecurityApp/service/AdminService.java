package ru.zenchenko.springcourse.SecondSecurityApp.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN')") //Проверяем роль
    public void doAdminStuff(){
        log.info("doing admin stuff");
        System.out.println("only admin here");
    }
}
