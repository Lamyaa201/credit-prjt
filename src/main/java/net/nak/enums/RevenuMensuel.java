package net.nak.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RevenuMensuel {

    O( "O", "MENAGE"),        // Enum constant representing household income with code "O"
    N( "N", "INDIVIDUEL");    // Enum constant representing individual income with code "N"

    private final String label;
    private final String code; // Ajout du champ code

    RevenuMensuel( String code, String label) {
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
    public static RevenuMensuel fromCode(String code) {
        for (RevenuMensuel revenuMensuel : RevenuMensuel.values()) {
            if (revenuMensuel.code.equals(code)) {
                return revenuMensuel;
            }
        }
        throw new IllegalArgumentException("Code Revenu Mensuel invalide : " + code);
    }}
