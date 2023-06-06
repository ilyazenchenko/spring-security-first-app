package ru.zenchenko.springcourse.SecondSecurityApp.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zenchenko.springcourse.SecondSecurityApp.models.Person;
import ru.zenchenko.springcourse.SecondSecurityApp.repositories.PeopleRepository;

@Service
@Slf4j
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Transactional
    public void register(Person person){
        log.info("register person");
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
        emailService.sendEmail("ilya.zenchenko@icloud.com", "Пользователь сохранен","Ваш " +
                "пользователь добавлен!!!");
    }

}
