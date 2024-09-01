package net.nak.DTO;

import lombok.Getter;
import lombok.Setter;
import net.nak.entities.ProduitParticulier;
import net.nak.enums.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class DemandeGarantieFOGDTO extends AnnexeDTO{
    private String nom;
    private String prenom;
    private String numCIN;
    private Sexe sexe;
    private Date dateNaissance;
    private String profession;
    private String numCreditBq;
    private Double montant;
    private String duree;
    private Double quotiteFinancement;
    private ObjetCredit objetCredit;
    private Double tauxInteret;
    private Double tauxInteretRetard;
    private Double coutGlobal;
    private Double prix;
    private String superficie;
    private Integer codeVille;
    private String numTitreFoncier;
    private Double fraisCapitale;
    private TypeLogement typeLogement;
    private RevenuMensuel revenuMensuel;
    private Boolean marie;
    private Double revenuConjoint;
    private Double nbrPrsnCharge;
    private String ancienneteBancaire;
    private String adresseLogmeent;
    private String vendeurLogemet;
    private Integer differe;
    private Aquisition aquisitionIndivision;
    private TypePrime typePrime;
    private Double prixTerrain;
    private NatureTF natureTF;
    private String paysAccueil;

    private Boolean isActive = true;
    private List<ProduitParticulier> produitParticulier;


}
