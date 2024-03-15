package com.challenge.totvscrud.entity;

import com.challenge.totvscrud.entity.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private Long id;
    private String nomeCliente;
    private String cpfCliente;
    private String enderecoCliente;
    private String bairroCliente;
    private List<Telefone> telefones;

    public Cliente(ClienteDTO clienteDTO) {
        this.nomeCliente = clienteDTO.nome();
        this.cpfCliente = clienteDTO.cpf();
        this.enderecoCliente = clienteDTO.endereco();
        this.bairroCliente = clienteDTO.bairro();
    }
}
