package net.nak.enums;

public enum TypeAction {
    ASSIGNATION_EN_PAYEMENT("A", "Assignation en payement"),
    REALISATION_DE_SURETE("R", "Réalisation de sûreté");

    private final String code;
    private final String description;

    TypeAction(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

    public static TypeAction fromCode(String code) {
        for (TypeAction action : TypeAction.values()) {
            if (action.getCode().equals(code)) {
                return action;
            }
        }
        throw new IllegalArgumentException("No matching enum for code " + code);
    }
}
