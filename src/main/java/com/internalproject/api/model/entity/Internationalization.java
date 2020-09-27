package com.internalproject.api.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "internationalization")
@Getter
@Setter
public class Internationalization {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "internationalization_internationalization_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column(name = "internationalization_id")
    private Long id;
    @Column(name = "word_en")
    private String wordEn;
    @Column(name = "word_Ru")
    private String wordRu;
    @Column(name = "key_word")
    private String wordKey;
}
