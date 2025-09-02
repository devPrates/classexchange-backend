package com.ClassExchange.domain.enums;

public enum MedidaTempo {
    MINUTOS(0),
    HORAS(1);

    private final int value;

    MedidaTempo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}