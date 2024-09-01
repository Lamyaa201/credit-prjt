package net.nak.Schedule;

import net.nak.entities.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class GeneralItemProcessor implements ItemProcessor<Object, String> {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public String process(Object item) throws Exception {
        if (item instanceof EtatImpayes) {
            return processEtatImpayes((EtatImpayes) item);
        } else if (item instanceof EtatReglementPrime) {
            return processEtatReglementPrime((EtatReglementPrime) item);
        } else if (item instanceof ChangementDebiteur) {
            return processChangementDebiteur((ChangementDebiteur) item);
        } else if (item instanceof DemandeMEJGarantie) {
            return processDemandeMEJGarantie((DemandeMEJGarantie) item);
        } else if (item instanceof DemandeGarantieFOG) {
            return processDemandeGarantieFOG((DemandeGarantieFOG) item);
        } else if (item instanceof EtatAnnulationMEJ) {
            return processEtatAnnulationMEJ((EtatAnnulationMEJ) item);
        } else if (item instanceof DetailReglementRistourne) {
            return processDetailReglementRistourne((DetailReglementRistourne) item);
        } else if (item instanceof EtatRecouvrementRealise) {
            return processEtatRecouvrementRealise((EtatRecouvrementRealise) item);
        } else if (item instanceof RestitutionMEJ) {
            return processRestitutionMEJ((RestitutionMEJ) item);
        } else if (item instanceof ReglementMEJ) {
            return processReglementMEJ((ReglementMEJ) item);
        }
        return null;
    }

    private String processEtatImpayes(EtatImpayes etatImpayes) {
        if (!Boolean.TRUE.equals(etatImpayes.getIsActive())) {
            return null;
        }
        return String.format("%s#%s#%s#%s",
                etatImpayes.getNumCIN(),
                etatImpayes.getNumCredit(),
                formatDate(etatImpayes.getDateImpaye()),
                formatAmount(etatImpayes.getPrincipalImpaye())
        );
    }

    private String processEtatReglementPrime(EtatReglementPrime etatReglementP) {
        return String.format("%s#%s#%s#%s#%s#%s",
                etatReglementP.getNumCin(),
                etatReglementP.getNumCredit(),
                formatDate(etatReglementP.getDateEcheance()),
                formatAmount(etatReglementP.getMontantRegle()),
                etatReglementP.getRefReglement(),
                formatDate(etatReglementP.getDateReglement())
        );
    }

    private String processChangementDebiteur(ChangementDebiteur changementDebiteur) {
        return String.format("%s#%s#%s#%s#%s",
                changementDebiteur.getNumCIN(),
                changementDebiteur.getNumCredit(),
                String.valueOf(changementDebiteur.getDebiteurInit()),
                String.valueOf(changementDebiteur.getNouveauDebit()),
                formatDate(changementDebiteur.getDateEffetTransfert())
        );
    }

    private String processDemandeMEJGarantie(DemandeMEJGarantie demandeMEJGarantie) {
        return String.format("%s#%s#%s#%s#%s#%s#%s",
                demandeMEJGarantie.getNumCIN(),
                demandeMEJGarantie.getNumCredit(),
                formatAmount(demandeMEJGarantie.getMontant()),
                formatDate(demandeMEJGarantie.getDateEcheance()),
                formatDate(demandeMEJGarantie.getDatePremEchImpaye()),
                formatAmount(demandeMEJGarantie.getMontantRestant()),
                formatAmount(demandeMEJGarantie.getMontantReclame())
        );
    }

    private String processDemandeGarantieFOG(DemandeGarantieFOG demandeGarantieFOG) {
        return String.join("#",
                demandeGarantieFOG.getNom(),
                demandeGarantieFOG.getPrenom(),
                demandeGarantieFOG.getNumCIN(),
                String.valueOf(demandeGarantieFOG.getSexe()),
                formatDate(demandeGarantieFOG.getDateNaissance()),
                demandeGarantieFOG.getProfession(),
                demandeGarantieFOG.getNumCreditBq(),
                formatAmount(demandeGarantieFOG.getMontant()),
                demandeGarantieFOG.getDuree(),
                formatAmount(demandeGarantieFOG.getQuotiteFinancement()),
                String.valueOf(demandeGarantieFOG.getObjetCredit()),
                formatAmount(demandeGarantieFOG.getTauxInteret()),
                formatAmount(demandeGarantieFOG.getTauxInteretRetard()),
                formatAmount(demandeGarantieFOG.getCoutGlobal()),
                formatAmount(demandeGarantieFOG.getPrix()),
                demandeGarantieFOG.getSuperficie(),
                Optional.ofNullable(demandeGarantieFOG.getCodeVille()).map(Object::toString).orElse("N/A"),
                demandeGarantieFOG.getNumTitreFoncier(),
                formatAmount(demandeGarantieFOG.getFraisCapitale()),
                String.valueOf(demandeGarantieFOG.getTypeLogement()),
                String.valueOf(demandeGarantieFOG.getRevenuMensuel()),
                String.valueOf(demandeGarantieFOG.getMarie()),
                formatAmount(demandeGarantieFOG.getRevenuConjoint()),
                formatAmount(demandeGarantieFOG.getNbrPrsnCharge()),
                demandeGarantieFOG.getAncienneteBancaire(),
                demandeGarantieFOG.getAdresseLogmeent(),
                demandeGarantieFOG.getVendeurLogemet(),
                Optional.ofNullable(demandeGarantieFOG.getDiffere()).map(Object::toString).orElse("0"),
                String.valueOf(demandeGarantieFOG.getAquisitionIndivision()),
                String.valueOf(demandeGarantieFOG.getTypePrime()),
                formatAmount(demandeGarantieFOG.getPrixTerrain()),
                String.valueOf(demandeGarantieFOG.getNatureTF()),
                demandeGarantieFOG.getPaysAccueil()
        );
    }

    private String processEtatAnnulationMEJ(EtatAnnulationMEJ etatAnnulationMEJ) {
        return String.format("%s#%s",
                etatAnnulationMEJ.getIdCredit(),
                String.valueOf(etatAnnulationMEJ.getCodeMotif())
        );
    }

    private String processDetailReglementRistourne(DetailReglementRistourne dReglementR) {
        return String.format("%s#%s#%s#%s#%s",
                dReglementR.getIdCredit(),
                formatDate(dReglementR.getDateEcheance()),
                formatAmount(dReglementR.getMontantRistoune()),
                formatDate(dReglementR.getDateReglement()),
                dReglementR.getRefReglement()
        );
    }

    private String processEtatRecouvrementRealise(EtatRecouvrementRealise etatRecouvrementR) {
        return String.format("%s#%s#%s#%s#%s#%s#%s",
                etatRecouvrementR.getIdCredit(),
                formatAmount(etatRecouvrementR.getRecouvrementRealise()),
                formatAmount(etatRecouvrementR.getMontantFrais()),
                formatAmount(etatRecouvrementR.getPartTamwil()),
                formatDate(etatRecouvrementR.getDateRecouvrementBq()),
                formatDate(etatRecouvrementR.getDateVirementTamwil()),
                etatRecouvrementR.getRefReglement()
        );
    }


    private String processRestitutionMEJ(RestitutionMEJ restitutionM) {
        return String.format("%s#%s#%s#%s",
                restitutionM.getIdCredit(),
                formatAmount(restitutionM.getMontantRest()),
                formatDate(restitutionM.getDateRestitution()),
                restitutionM.getRefRestitution()
        );
    }

    private String processReglementMEJ(ReglementMEJ reglementM) {
        return String.format("%s#%s#%s#%s",
                reglementM.getIdCredit(),
                formatDate(reglementM.getDateReglement()),
                formatAmount(reglementM.getMontantMEJ()),
                reglementM.getRefReglement()
        );
    }

    private String formatDate(Date date) {
        return date != null ? new SimpleDateFormat(DATE_FORMAT).format(date) : "N/A";
    }

    private String formatAmount(Double amount) {
        return amount != null ? String.format("%.2f", amount) : "0.00";
    }
}
