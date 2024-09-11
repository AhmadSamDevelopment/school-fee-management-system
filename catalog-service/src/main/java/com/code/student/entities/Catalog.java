package com.code.student.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "catalogs")
public class Catalog extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "activation_date")
    private LocalDateTime activationDate;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "grade_id")
    private Long grade;

    @Column(name = "is_active", columnDefinition = "bit not null default 1")
    private boolean active = true;

    @OneToMany(mappedBy = "catalog")
    private List<Item> items;


}
