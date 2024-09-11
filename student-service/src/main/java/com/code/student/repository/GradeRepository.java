package com.code.student.repository;

import com.code.student.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GradeRepository extends JpaRepository<Grade, Long>,
        JpaSpecificationExecutor<Grade> {

}
