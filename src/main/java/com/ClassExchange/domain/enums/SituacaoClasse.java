package com.ClassExchange.domain.enums;

public enum SituacaoClasse {
    EM_CURSO(0),
    EVADIDO(1);

    private final int value;

    SituacaoClasse(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}