package com.fudn.jpa_demo;

import com.fudn.jpa_demo.entity.Student;
import com.fudn.jpa_demo.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaDemoApplicationTests {

    @Autowired
    private StudentService studentService;

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void testCreateAndRetrieveStudentFromDatabase() {
        studentService.createStudent("Chuuni Chinori", "chuunicute@gmail.com", 13);

        Student s = em.find(Student.class, 3L);

        assertNotNull(s, "Student should exist in the database");
        assertEquals("Chuuni Chinori", s.getFullName());
        assertEquals("chuunicute@gmail.com", s.getEmail());
        assertEquals(13, s.getAge());

        em.flush(); em.clear();
    }

    @Test
    @Transactional
    public void testUpdateStudentFromDatabase() {
        studentService.updateStudent(1L, "Aprila Yuugure", "aprila@gmail.com", 15);

        Student s = em.find(Student.class, 1L);

        assertNotNull(s, "Student should exist in the database");
        assertEquals("Aprila Yuugure", s.getFullName());
        assertEquals("aprila@gmail.com", s.getEmail());
        assertEquals(15, s.getAge());

        em.flush(); em.clear();
    }

    @Test
    @Transactional
    public void testDeleteStudentFromDatabase() {
        Student s;

        s = em.find(Student.class, 2L);

        assertNotNull(s, "Student should exist in the database");

        studentService.deleteStudent(2L);

        s = em.find(Student.class, 2L);

        assertNull(s, "Student is removed from the database");

        em.flush(); em.clear();
    }
}
