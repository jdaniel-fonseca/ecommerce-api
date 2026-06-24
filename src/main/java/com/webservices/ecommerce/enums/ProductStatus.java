package com.webservices.ecommerce.enums;

public enum ProductStatus {

    ATIVO(1),
    INATIVO(2),
    ESGOTADO(3);

    private int valor;

    private ProductStatus(int valor) {
        this.valor = valor;
    }

    public int getValue() {
        return valor;
    }

    public static ProductStatus valueOf(int valor) {
        for (ProductStatus status : ProductStatus.values()) {
            if (status.getValue() == valor) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido.");
    }

}
