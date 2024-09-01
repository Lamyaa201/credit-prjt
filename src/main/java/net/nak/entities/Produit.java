package net.nak.entities;

import javax.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name="Produit")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produit_id")
    private Long id;

    @Column(name = "code_produit")
    private String codeProduit;

    @Column(name = "nom_produit")
    private String nom;

    @Column(name = "date_creation")
    private Date date;

    @Column(name = "description")
    private String description;

}
