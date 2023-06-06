package ru.zenchenko.springcourse.SecondSecurityApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.zenchenko.springcourse.SecondSecurityApp.models.Person;
import ru.zenchenko.springcourse.SecondSecurityApp.repositories.PeopleRepository;
import ru.zenchenko.springcourse.SecondSecurityApp.security.PersonDetails;

import java.util.Optional;

@Service
@Slf4j
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loading user details by username");
        Optional<Person> personOptional = peopleRepository.findByUsername(username);
        if (personOptional.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(personOptional.get());
    }
}