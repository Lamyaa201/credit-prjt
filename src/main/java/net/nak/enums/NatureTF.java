package net.nak.enums;

public enum NatureTF {
    M("M", "Mère"),
    P("P", "Parcellaire");

    private String code;
    private String label;

    NatureTF(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public static NatureTF fromCode(String code) {
        for (NatureTF nature : NatureTF.values()) {
            if (nature.code.equals(code)) {
                return nature;
            }
        }
        throw new IllegalArgumentException("Code NatureTF invalide : " + code);
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}
