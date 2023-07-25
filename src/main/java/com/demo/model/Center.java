package com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "centers")
@Data
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "address")
    private String address;
}
