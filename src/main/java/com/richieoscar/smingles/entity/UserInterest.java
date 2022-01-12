package com.richieoscar.smingles.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "interest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "likes")
    private String likes;
    @Column(name = "dislikes")
    private String disLikes;
    @Column(name = "hobbies")
    private String hobbies;
    @Column(name = "profile_url")
    private String profileUrl;
    @Column(name = "about")
    private String about;
    @Transient
    private int userId;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserAccount userAccount;


}
