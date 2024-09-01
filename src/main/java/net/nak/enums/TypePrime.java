package net.nak.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TypePrime {
    FLAT_LINEAIRE(0, "Flat linéaire"),
    PERIODIQUE_LINEAIRE(1, "Périodique linéaire"),
    PERIODIQUE_DEGRESSIVE(2, "Périodique dégressive"),
    FLAT_DEGRESSIVE(3, "Flat dégressive");

    private final int code;
    private final String label;

    TypePrime(int code, String label) {
        this.code = code;
        this.label = label;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    @JsonCreator
    public static TypePrime fromCode(int code) {
        for (TypePrime typePrime : TypePrime.values()) {
            if (typePrime.code == code) {
                return typePrime;
            }
        }
        throw new IllegalArgumentException("Code typePrime invalide : " + code);
    }
}
