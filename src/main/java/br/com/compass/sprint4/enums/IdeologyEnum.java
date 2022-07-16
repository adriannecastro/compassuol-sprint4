package br.com.compass.sprint4.enums;

public enum IdeologyEnum {

    DIREITA("Direita"),
    CENTRO("Centro"),
    ESQUERDA("Esquerda");

    private String value;

    IdeologyEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

