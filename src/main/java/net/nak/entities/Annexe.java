package net.nak.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name="Annexe")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Annexe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "annexe_id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "code_bq")
    private String codeBq;

    @Column(name = "numero_annexe")
    private String numeroAnnexe;
}
