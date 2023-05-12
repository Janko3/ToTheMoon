package com.example.tothemoon.model;

import com.example.tothemoon.model.enums.EReactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "reaction")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "type",nullable = false)
    @Enumerated(EnumType.STRING)
    private EReactionType type;
    @Column(name = "timestamp")
    private LocalDate timestamp;
    @Column(name = "deleted")
    private boolean isDeleted;
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @ManyToOne
    private User user;
    @JoinColumn(name = "comment_id",referencedColumnName = "id")
    @ManyToOne
    private Comment comment;
    @JoinColumn(name = "post_id",referencedColumnName = "id")
    @ManyToOne
    private Post post;
}
