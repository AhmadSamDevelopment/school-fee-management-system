package com.code.student.repository;

import com.code.student.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Long>,
        JpaSpecificationExecutor<Catalog> {

    @Query("SELECT c FROM Catalog c WHERE c.active = true AND :currentDate BETWEEN c.activationDate AND c.expiryDate and c.grade= :grade")
    List<Catalog> findActiveCatalogs(@Param("currentDate") LocalDateTime currentDate, @Param("grade") Long grade);
}
