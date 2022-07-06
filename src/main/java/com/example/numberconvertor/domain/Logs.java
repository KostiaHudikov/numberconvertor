package com.example.numberconvertor.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "convertationlogs")
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "type")
    private String type;

    @Column(name = "inner_value")
    private String innerVal;

    @Column(name = "outer_value")
    private String outerVal;

    @Column(name = "create_date")
    private Date date;
}
