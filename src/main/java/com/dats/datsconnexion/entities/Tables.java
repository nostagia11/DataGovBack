package com.dats.datsconnexion.entities;


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
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String techName;
    private String natName;
    private String type;
    private String format;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "table")
    private Set<Columns> listecolumns;

    @ManyToOne
    private TechStock techStockT;

}
