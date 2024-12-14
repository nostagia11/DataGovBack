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

    private String type; // e.g., SQL Server, MySQL
    private String connMethod; // e.g., "JDBC"
    private String url; // e.g., "jdbc:sqlserver://host:port;databaseName=dbName"
    private String host;
    private String infrastructure; // Cloud or On-Premise

    private String username;
    private String ePassword; // Store the password securely
    private int port;
    private String dbName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "techStock")
    private Set<SoftwareSolution> listesoftwares;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "techStockT")
    private Set<Tables> listetables;
}
