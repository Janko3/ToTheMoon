package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Banned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "timestamp")
    private LocalDate timestamp;
    @ManyToOne
    private GroupAdmin groupAdmin;
    @ManyToOne
    User user;
    @ManyToOne
    Group group;

}
