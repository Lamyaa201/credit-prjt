package net.nak.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ObjetCredit {
    ACHAT_LOGEMENT(1, "Achat de logement"),
    CONSTRUCTION_LOGEMENT(2, "Construction de logement"),
    ACHAT_TERRAIN_CONSTRUCTION(3, "Achat de terrain et sa construction");

    private final int code;
    private final String label;

    ObjetCredit(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    @JsonCreator
    public static ObjetCredit fromCode(int code) {
        for (ObjetCredit objetCredit : ObjetCredit.values()) {
            if (objetCredit.code == code) {
                return objetCredit;
            }
        }
        throw new IllegalArgumentException("Code ObjetCredit invalide : " + code);
    }
}
