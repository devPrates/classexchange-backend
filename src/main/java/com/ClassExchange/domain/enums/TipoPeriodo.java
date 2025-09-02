package com.ClassExchange.domain.enums;

public enum TipoPeriodo {
    ANUAL(0),
    SEMESTRAL(1),
    BIMESTRAL(2);

    private final int value;

    TipoPeriodo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}