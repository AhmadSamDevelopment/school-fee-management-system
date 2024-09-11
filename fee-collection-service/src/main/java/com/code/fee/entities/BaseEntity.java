package com.code.fee.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "updated_date")
    @LastModifiedDate
    private Instant updatedDate;

}
