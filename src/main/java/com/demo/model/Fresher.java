package com.demo.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "freshers")
@Data
public class Fresher {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @Column(name = "program")
    private String program;
    @Column(name = "email")
    private String email;
    @Column(name = "project1_point")
    private Float project1Point;
    @Column(name = "project2_point")
    private Float project2Point;
    @Column(name = "project3_point")
    private Float project3Point;
    @Column(name = "avg_point")
    private Float avgPoint;
    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
}