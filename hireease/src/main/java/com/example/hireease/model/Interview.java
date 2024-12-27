package com.example.hireease.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "interviews")
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interviewName;

    private Date date_time;

    private String type;

    private String report;

    public Interview(Object o, String technicalInterview, Date date, String online, String passed, Object o1, Object o2) {
    }
}
