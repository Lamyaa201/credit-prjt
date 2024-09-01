package net.nak.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Sexe {
    M("M", "MASCULIN"),
    F("F", "FEMININ");

    private final String code;
    private final String label;

    Sexe(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static Sexe fromCode(String code) {
        for (Sexe sexe : Sexe.values()) {
            if (sexe.code.equals(code)) {
                return sexe;
            }
        }
        throw new IllegalArgumentException("Code sexe invalide : " + code);
    }
}
