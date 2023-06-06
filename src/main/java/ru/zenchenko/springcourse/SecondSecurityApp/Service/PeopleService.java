package ru.zenchenko.springcourse.SecondSecurityApp.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zenchenko.springcourse.SecondSecurityApp.models.Person;
import ru.zenchenko.springcourse.SecondSecurityApp.repositories.PeopleRepository;

import java.util.Optional;

@Service
@Slf4j
public class PeopleService  {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findByUsername(String username){
        log.info("finding by username");
        return peopleRepository.findByUsername(username);
    }
}
