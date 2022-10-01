package com.eluanps.travelapp.entity.enums;

public enum PagamentoStatus {

    PENDENTE(1, "Pendente"),
    PAGO(2, "pago"),
    CANCELADO(3, "Cancelado");

    private int cod;
    private String descricao;

    private PagamentoStatus(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PagamentoStatus toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (PagamentoStatus status : PagamentoStatus.values()) {
            if (cod.equals(status.getCod())) {
                return status;
            }

        }

        throw new IllegalArgumentException("Cód. inválido: " + cod);

    }

}
