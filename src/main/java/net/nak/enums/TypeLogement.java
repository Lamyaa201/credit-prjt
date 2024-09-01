package net.nak.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TypeLogement {
    LOCATAIRE(0, "Locataire"),
    BIDONVILLOI(1, "Bidonvilloi"),
    MRE(2, "MRE"),
    BENEFICIAIRE_VSB_DESCENDANT(3, "Bénéficiaire VSB Descendant");

    private final int code;
    private final String label;

    TypeLogement(int code, String label) {
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
    public static TypeLogement fromCode(int code) {
        for (TypeLogement typeLogement : TypeLogement.values()) {
            if (typeLogement.code == code) {
                return typeLogement;
            }
        }
        throw new IllegalArgumentException("Code TypeLogement invalide : " + code);
    }
}
