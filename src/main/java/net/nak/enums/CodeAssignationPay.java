package net.nak.enums;

public enum CodeAssignationPay {
    DEPOT_DE_REQUETE(19, "Dépôt de requête"),
    JUGEMENT(21, "Jugement"),
    EXECUTIONS(24, "Exécutions");

    private final int code;
    private final String description;

    CodeAssignationPay(int code, String description) {
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
    public static CodeAssignationPay fromCode(int code) {
        for (CodeAssignationPay assignation : CodeAssignationPay.values()) {
            if (assignation.getCode() == code) {
                return assignation;
            }
        }
        throw new IllegalArgumentException("No matching enum for code " + code);
    }
}
