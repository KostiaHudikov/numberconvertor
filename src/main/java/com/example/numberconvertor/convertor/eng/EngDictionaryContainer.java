package com.example.numberconvertor.convertor.eng;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "engdictionarycontainer")
public class EngDictionaryContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "intnumber")
    private int intnumber;

    @Column(name = "stringnumber")
    private String stringnumber;

    @Column(name = "numbertype")
    private String numbertype;

    @Column(name = "fullnumber")
    private String fullnumber;
}
