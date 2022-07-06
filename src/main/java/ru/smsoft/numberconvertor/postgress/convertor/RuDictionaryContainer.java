package ru.smsoft.numberconvertor.postgress.convertor;

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
    private long intnumber;

    @Column(name = "stringnumber")
    private String stringnumber;

    @Column(name = "numbertype")
    private String numbertype;

    @Column(name = "fullnumber")
    private String fullnumber;
}
