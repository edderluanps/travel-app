package com.eluanps.travelapp.entity.enums;

public enum TipoPerfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    private TipoPerfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPerfil toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (TipoPerfil tipo : TipoPerfil.values()) {
            if (cod.equals(tipo.getCod())) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
