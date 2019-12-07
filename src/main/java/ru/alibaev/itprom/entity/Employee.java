package ru.alibaev.itprom.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fio;
    @ManyToOne
    private Profession profession;
    @ManyToOne
    private Department department;
    private String note;
}
