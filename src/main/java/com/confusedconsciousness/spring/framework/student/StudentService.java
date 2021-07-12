package com.confusedconsciousness.spring.framework.student;

import java.util.List;
import java.util.Optional;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void registerNewStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent())
            throw new IllegalStateException("Email Already Taken");
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Boolean exists = studentRepository.existsById(studentId);
        if (!exists)
            throw new IllegalStateException("Student doesn't exist");
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String emailId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()
                -> new IllegalStateException("student doesn't exists"));
        if (!Strings.isNullOrEmpty(name))
            student.setName(name);
        if (!Strings.isNullOrEmpty(emailId)) {
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(emailId);
            if (optionalStudent.isPresent())
                throw new IllegalStateException("Email is already used");
            student.setEmail(emailId);
        }
    }
}
