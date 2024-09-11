package com.code.student.repository;

import com.code.student.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemRepository extends JpaRepository<Item, Long>,
        JpaSpecificationExecutor<Item> {

}
