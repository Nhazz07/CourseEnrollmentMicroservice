package org.example.studentservice.Repository;

import org.example.studentservice.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
}
