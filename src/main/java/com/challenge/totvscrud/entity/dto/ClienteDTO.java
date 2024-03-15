package com.challenge.totvscrud.entity.dto;

import java.util.List;

public record ClienteDTO (
        Long id,
        String nome,
        String cpf,
        String endereco,
        String bairro,
        List<TelefoneDTO> telefones
) {}
