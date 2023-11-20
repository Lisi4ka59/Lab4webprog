package com.lisi4ka.lab4webdb.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Data
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString
//@RequiredArgsConstructor
public class Dot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long count;
    private float X, Y, R;
    private int userId, red, green, blue;
    private long flightTime;
    private String result;
    private Date date;
}
