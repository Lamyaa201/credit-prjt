package net.nak.enums;

public enum CodeEvenement {
    REMBOURSEMENT_TOTAL_ANTICIPATION(1),
    DECES_BENEFICIAIRE(2),
    CESSION_LOGEMENT(3),
    DESISTEMENT(4),
    DECHEANCE_TERME(5),
    REMBOURSEMENT_PARTIEL_ANTICIPATION(6),
    REECHELONNEMENT(7),
    ANNULATION_ECHEANCIER(8);

    private final int code;

    CodeEvenement(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static CodeEvenement fromCode(int code) {
        for (CodeEvenement evenement : CodeEvenement.values()) {
            if (evenement.code == code) {
                return evenement;
            }
        }
        throw new IllegalArgumentException("Code evenement invalide : " + code);
    }
}
