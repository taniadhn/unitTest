package com.dehghan.unittest.student;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name;

    @Email
    @Column(name = "EMAIL", nullable = false, unique = false)
    private String email;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false)
    private Gender gender;

    public StudentEntity(String name, String email, Gender gender) {

        this.name = name;
        this.email = email;
        this.gender = gender;
    }
}
