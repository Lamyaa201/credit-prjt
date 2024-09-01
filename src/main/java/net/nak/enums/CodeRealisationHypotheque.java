package net.nak.enums;

public enum CodeRealisationHypotheque {
    VENTE(5, "Vente"),
    COMMANDEMENT_IMMOBILIER(7, "Commandement immobilier"),
    EXPERTISE(16, "Expertise");

    private final int code;
    private final String description;

    CodeRealisationHypotheque(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

    // Optional: Method to get an enum by its code
    public static CodeRealisationHypotheque fromCode(int code) {
        for (CodeRealisationHypotheque realisation : CodeRealisationHypotheque.values()) {
            if (realisation.getCode() == code) {
                return realisation;
            }
        }
        throw new IllegalArgumentException("No matching enum for code " + code);
    }
}
