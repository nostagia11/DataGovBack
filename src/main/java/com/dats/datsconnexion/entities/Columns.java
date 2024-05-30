package com.dats.datsconnexion.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = {"idCompte"})
//@EqualsAndHashCode(exclude = {"idCompte"})
@Table
@DynamicInsert
@DynamicUpdate

public class Columns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tname;
    private String syno;
    private String type;

    @JsonBackReference(value="table")

    @ManyToOne
    private Tables table;


    @JsonBackReference(value="label")

    @ManyToOne
    private Label label;
}
