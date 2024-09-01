package net.nak.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Debiteur {
    ETUDIANT(1, "Étudiant"),
    TUTEUR(2, "Tuteur"),
    ETUDIANT_ET_TUTEUR(3, "Étudiant et Tuteur");

    private final int code;
    private final String label;

    Debiteur(int code, String label) {
        this.code = code;
        this.label = label;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static Debiteur fromCode(int code) {
        for (Debiteur debiteur : Debiteur.values()) {
            if (debiteur.code == code) {
                return debiteur;
            }
        }
        throw new IllegalArgumentException("Code debiteur invalide : " + code);
    }
}
