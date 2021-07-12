package com.confusedconsciousness.spring.framework.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student a = Student.builder()
                    .name("Kishan Kumar")
                    .email("kishan.kumar@phonepe.com")
                    .id(1)
                    .dob(LocalDate.of(1996, Month.SEPTEMBER, 5))
                    .build();
            Student b = Student.builder()
                    .name("Muskan Mittal")
                    .email("muskan.mittal@microsoft.com")
                    .id(2)
                    .dob(LocalDate.of(1994, Month.AUGUST, 1))
                    .build();

            repository.saveAll(List.of(a, b));
        };
    }

}
