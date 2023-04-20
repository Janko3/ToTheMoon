package com.example.tothemoon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "group_admin")
public class GroupAdmin {
    @Id
    @ManyToOne
    private User user;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "group_admin")
    private Set<Banned> by = new HashSet<>();
}
