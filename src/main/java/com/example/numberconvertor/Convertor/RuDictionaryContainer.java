package com.example.numberconvertor.Convertor;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rudictionarycontainer")
public class RuDictionaryContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "intnumber")
    private int intnumber;

    @Column(name = "stringnumber")
    private String stringnumber;

    @Column(name = "numbertype")
    private String numbertype;
}
