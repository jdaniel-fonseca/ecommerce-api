package com.webservices.ecommerce.enums;

public enum OrderStatus {

    AGUARDANDO_PAGAMENTO(1),
    PAGO(2),
    ENVIADO(3),
    ENTREGUE(4),
    CANCELADO(5);

    private int value;

    private OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderStatus getOrderStatus(int value) {
        for (OrderStatus values : OrderStatus.values()) {
            if (values.getValue() == value) {
                return values;
            }
        }
        throw new IllegalArgumentException("Código de pedido inválido.");
    }
}
