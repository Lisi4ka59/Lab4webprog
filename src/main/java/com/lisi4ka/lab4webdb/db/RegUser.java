package com.lisi4ka.lab4webdb.db;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString
//@RequiredArgsConstructor
public class RegUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
}