package com.code.student.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "catalog_items")
public class Item extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "catalog_id", referencedColumnName = "id")
    private Catalog catalog;

}
