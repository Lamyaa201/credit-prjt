package net.nak.enums;

public enum TypeCredit {
    A(1, "Crédit plafonné à 100 KDH + Ristourne totale + Durée maximale de 10 ans"),
    B(2, "Crédit complémentaire plafonné à 150 KDH + Ristourne 2% + Durée maximale de 20 ans");

    private final int code;
    private final String description;

    TypeCredit(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TypeCredit getByCode(int code) {
        for (TypeCredit typeCredit : TypeCredit.values()) {
            if (typeCredit.getCode() == code) {
                return typeCredit;
            }
        }
        throw new IllegalArgumentException("Code de type de crédit invalide : " + code);
    }
}
