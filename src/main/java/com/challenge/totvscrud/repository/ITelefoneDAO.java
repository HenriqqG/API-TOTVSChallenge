package com.challenge.totvscrud.repository;

import com.challenge.totvscrud.entity.Telefone;

import java.util.List;

public interface ITelefoneDAO {
    List<Telefone> findAllByClienteId(Long idCliente);
    void insert(Telefone telefone);
    void remove(Long id);
}
