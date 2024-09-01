package net.nak.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CodeMotif {
    Apurement(1, "Apurement"),
    Reechelonnement_Consolidation(2, "Rééchelonnement / Consolidation");

    private final int code;
    private final String label;

    CodeMotif(int code, String label) {
        this.code = code;
        this.label = label;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    @JsonCreator
    public static CodeMotif fromCode(int code) {
        for (CodeMotif codeMotif : CodeMotif.values()) {
            if (codeMotif.code == code) {
                return codeMotif;
            }
        }
        throw new IllegalArgumentException("Code motif invalide : " + code);
    }

    public String getLabel() {
        return label;
    }
}
