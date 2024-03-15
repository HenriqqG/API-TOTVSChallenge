package com.challenge.totvscrud.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Data Transfer Object (DTO) para representar um cliente.
 */
public record ClienteDTO (
        @Schema(description = "ID do cliente")
        Long id,
        @Schema(description = "Nome do cliente")
        String nome,
        @Schema(description = "CPF do cliente")
        String cpf,
        @Schema(description = "Endereço do cliente")
        String endereco,
        @Schema(description = "Bairro do cliente")
        String bairro,
        @Schema(description = "Lista de telefones do cliente")
        List<TelefoneDTO> telefones
) {}
