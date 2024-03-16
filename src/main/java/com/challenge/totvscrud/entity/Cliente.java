package com.challenge.totvscrud.entity;

import com.challenge.totvscrud.entity.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de entidade para representar um cliente.
 */
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

    /**
     * Construtor que recebe um ClienteDTO para inicializar a entidade Cliente.
     *
     * @param clienteDTO O ClienteDTO contendo os dados do cliente.
     */
    public Cliente(ClienteDTO clienteDTO) {
        this.nomeCliente = clienteDTO.nome();
        this.cpfCliente = clienteDTO.cpf();
        this.enderecoCliente = clienteDTO.endereco();
        this.bairroCliente = clienteDTO.bairro();
    }

    /**
     * Converte a entidade Cliente em um ClienteDTO.
     *
     * @return Um ClienteDTO correspondente à entidade Cliente.
     */
    public ClienteDTO ToDTO(){
        return new ClienteDTO(
                this.getId(), this.getNomeCliente(), this.getCpfCliente(),
                this.getEnderecoCliente(), this.getBairroCliente(),
                this.getTelefones().stream().map(Telefone::ToDTO).toList()
        );
    }
}
