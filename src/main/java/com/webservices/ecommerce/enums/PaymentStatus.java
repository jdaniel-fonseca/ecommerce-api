package com.webservices.ecommerce.enums;


public enum PaymentStatus {

    PENDENTE(1),
    APROVADO(2),
    RECUSADO(3),
    ESTORNADO(4);

    private int value;

    private PaymentStatus(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    public static PaymentStatus valueOf(int value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor inválido.");
    }
}
