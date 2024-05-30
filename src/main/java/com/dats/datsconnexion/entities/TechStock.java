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
public class TechStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String connMethod;
    private String url;
    private String local;
    private String infrastructure;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "techStock")
    private Set<SoftwareSolution> listesoftwares;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "techStockT")
    private Set<Tables> listetables;
}
