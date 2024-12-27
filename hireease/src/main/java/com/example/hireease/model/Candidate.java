package com.example.hireease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phone;

    private String address;

    private String email;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "resume_id")
//    private Resume resume;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "candidate_id")
    private List<Interview> interviews;

}
