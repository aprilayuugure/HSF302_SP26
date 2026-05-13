package com.fudn.jpa_demo.service;

import com.fudn.jpa_demo.entity.Student;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createStudent(String name, String email, int age) {
        Student s = new Student(name, email, age);
        em.persist(s);
        System.out.println("Saved with ID = " + s.getId());
    }

    @Transactional
    public void updateStudent(Long id, String fullName, String email, int age) {
        Student s = em.find(Student.class, id);

        if (s != null) {
            s.setFullName(fullName);
            s.setEmail(email);
            s.setAge(age);

            em.merge(s);
        }
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student s = em.find(Student.class, id);

        if (s != null) em.remove(s);
    }

    @Transactional(readOnly = true)
    public void printAll() {
        em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList()
                .forEach(System.out::println);
    }
}
