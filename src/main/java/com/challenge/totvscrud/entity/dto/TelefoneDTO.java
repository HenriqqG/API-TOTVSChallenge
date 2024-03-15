package com.challenge.totvscrud.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) para representar um telefone.
 */
public record TelefoneDTO(
        @Schema(description = "ID do telefone")
        Long id,
        @Schema(description = "Número do telefone")
        String numero,
        @Schema(description = "ID do cliente associado ao telefone")
        Long idCliente
) {}
