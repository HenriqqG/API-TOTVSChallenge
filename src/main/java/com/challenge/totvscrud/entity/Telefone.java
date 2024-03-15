package com.challenge.totvscrud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Telefone {
    private Long id;
    private String numeroTelefone;
    private Long idCliente;

    public Telefone(String numeroTelefone, Long idCliente) {
        this.numeroTelefone = numeroTelefone;
        this.idCliente = idCliente;
    }
}
