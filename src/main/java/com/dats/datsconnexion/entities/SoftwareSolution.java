package com.dats.datsconnexion.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class SoftwareSolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_SOFR;
    private String nameS;

    private String type;
    private String provider;
    private String costType;
    @CreationTimestamp
    private LocalDateTime registred_on;


    @JsonBackReference
    @ManyToOne
    private TechStock techStock;

}
