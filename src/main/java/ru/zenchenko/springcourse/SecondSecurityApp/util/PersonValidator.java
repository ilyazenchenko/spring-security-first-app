package ru.zenchenko.springcourse.SecondSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.zenchenko.springcourse.SecondSecurityApp.Service.PeopleService;
import ru.zenchenko.springcourse.SecondSecurityApp.models.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> optional = peopleService.findByUsername(person.getUsername());
        if (optional.isPresent())
            errors.rejectValue("username", "",
                    "Такое имя пользователя уже существует");
    }
}
