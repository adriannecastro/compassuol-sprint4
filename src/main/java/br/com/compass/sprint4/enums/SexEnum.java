package br.com.compass.sprint4.enums;

public enum SexEnum {

    MASCULINO ("Masculino"),
    FEMININO ("Feminino");

    private String value;

    SexEnum(String value) {
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
