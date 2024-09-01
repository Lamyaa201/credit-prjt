package net.nak.enums;

public enum MotifDemande {
    DECHEANCE_DU_TERME(1),
    OUVERTURE_DE_LA_PROCEDURE(2);

    private final int value;

    MotifDemande(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MotifDemande fromValue(int value) {
        for (MotifDemande motif : values()) {
            if (motif.getValue() == value) {
                return motif;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
