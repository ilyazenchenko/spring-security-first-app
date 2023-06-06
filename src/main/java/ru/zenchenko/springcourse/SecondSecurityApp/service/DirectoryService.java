package ru.zenchenko.springcourse.SecondSecurityApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.zenchenko.springcourse.SecondSecurityApp.models.Person;
import ru.zenchenko.springcourse.SecondSecurityApp.repositories.PeopleRepository;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class DirectoryService {
    private final PeopleRepository peopleRepository;
    private final String directoryPath;

    public DirectoryService(PeopleRepository peopleRepository, @Value("${directory.path}") String directoryPath) {
        this.peopleRepository = peopleRepository;
        this.directoryPath = directoryPath;
    }

    // Метод, который очищает директорию, создает по файлу для каждой из сущностей и
    // загружает туда все данные из базы данных
//    @Scheduled(fixedRate = 1800000)
    @Scheduled(fixedRate = 10000)
    public void updateFiles() {
        System.out.println("updatefiles");
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        for (Person person : peopleRepository.findAll()) {
            String fileName = person.getUsername() + ".txt";
            File file = new File(directory, fileName);
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(person.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод для вызова через JMX
    @ManagedOperation(description = "Update files")
    public void updateFilesJmx() {
        updateFiles();
    }
}

