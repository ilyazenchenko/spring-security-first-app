package ru.zenchenko.springcourse.SecondSecurityApp;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    @Before("allServiceMethods()")
    public void logParameters(JoinPoint joinPoint){
        log.info("Parameters: {}", joinPoint.getArgs());
    }

    @Pointcut("within(ru.zenchenko.springcourse.SecondSecurityApp.service.*)")
    public void allServiceMethods(){}
}
