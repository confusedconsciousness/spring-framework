package com.confusedconsciousness.spring.framework.student;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // this needs to be SELECT * FROM student WHERE email=?
    // this is JBQL
    @Query("SELECT s from Student s WHERE s.email=?1")
    Optional<Student> findStudentByEmail(String email);

}
