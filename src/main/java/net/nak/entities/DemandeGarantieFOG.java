package net.nak.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.nak.enums.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "DemandeGarantieFOG")
public class DemandeGarantieFOG extends Annexe {

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "num_cin")
    private String numCIN;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private Sexe sexe;

    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Column(name = "profession")
    private String profession;

    @Column(name = "num_credit_bq")
    private String numCreditBq;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "duree")
    private String duree;

    @Column(name = "quotite_financement")
    private Double quotiteFinancement;

    @Enumerated(EnumType.STRING)
    @Column(name = "objetCredit")
    private ObjetCredit objetCredit;

    @Column(name = "taux_interet")
    private Double tauxInteret;

    @Column(name = "taux_interet_retard")
    private Double tauxInteretRetard;

    @Column(name = "cout_global")
    private Double coutGlobal;

    @Column(name = "prix")
    private Double prix;

    @Column(name = "superficie")
    private String superficie;

    @Column(name = "code_ville")
    private Integer codeVille;

    @Column(name = "num_titre_foncier")
    private String numTitreFoncier;

    @Column(name = "frais_capitale")
    private Double fraisCapitale;

    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;

    @Enumerated(EnumType.STRING)
    private RevenuMensuel revenuMensuel;

    @Column(name = "marie")
    private Boolean marie;

    @Column(name = "revenu_conjoint")
    private Double revenuConjoint;

    @Column(name = "nbr_prsn_charge")
    private Double nbrPrsnCharge;

    @Column(name = "anciennete_bancaire")
    private String ancienneteBancaire;

    @Column(name = "adresse_logmeent")
    private String adresseLogmeent;

    @Column(name = "vendeur_logemet")
    private String vendeurLogemet;

    private Integer differe;

    @Enumerated(EnumType.STRING)
    private Aquisition aquisitionIndivision;

    @Enumerated(EnumType.STRING)
    private TypePrime typePrime;

    private Double prixTerrain;

    @Enumerated(EnumType.STRING)
    private NatureTF natureTF;

    private String paysAccueil;

    @Column(name = "isActive")
    private Boolean isActive = true;



    @OneToMany(mappedBy = "changementDebiteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProduitParticulier> produitParticulier;
}