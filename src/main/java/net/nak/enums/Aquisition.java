package net.nak.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Aquisition {

    O( "O", "Oui"),
    N( "N", "Non");

    private final String label;
    private final String code;

    Aquisition( String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static Aquisition fromCode(String code) {
        for (Aquisition aquisition : Aquisition.values()) {
            if (aquisition.code.equals(code)) {
                return aquisition;
            }
        }
        throw new IllegalArgumentException("Code Aquisition invalide : " + code);
    }

}
