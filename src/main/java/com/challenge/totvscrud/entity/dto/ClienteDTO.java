package com.challenge.totvscrud.entity.dto;

import java.util.List;

public record ClienteDTO (
        String nome,
        String cpf,
        String endereco,
        String bairro,
        List<TelefoneDTO> telefones
) {}
