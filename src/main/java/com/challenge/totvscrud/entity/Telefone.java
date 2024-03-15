package com.challenge.totvscrud.entity;

import com.challenge.totvscrud.entity.dto.TelefoneDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe de entidade para representar um telefone.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Telefone {
    private Long id;
    private String numeroTelefone;
    private Long idCliente;

    /**
     * Construtor que inicializa um objeto Telefone com o número do telefone e o ID do cliente associado.
     *
     * @param numeroTelefone O número do telefone.
     * @param idCliente      O ID do cliente associado ao telefone.
     */
    public Telefone(String numeroTelefone, Long idCliente) {
        this.numeroTelefone = numeroTelefone;
        this.idCliente = idCliente;
    }

    /**
     * Converte o objeto Telefone em um TelefoneDTO.
     *
     * @return Um TelefoneDTO correspondente ao objeto Telefone.
     */
    public TelefoneDTO ToDTO(){
        return new TelefoneDTO(
                this.getId(), this.getNumeroTelefone(), this.getIdCliente()
        );
    }
}
