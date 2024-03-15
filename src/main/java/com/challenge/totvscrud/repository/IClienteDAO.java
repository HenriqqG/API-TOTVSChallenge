package com.challenge.totvscrud.repository;

import com.challenge.totvscrud.entity.Cliente;

import java.util.List;

public interface IClienteDAO {
    List<Cliente> findAll();
    Cliente findById(Long id);
    Long insert(Cliente cliente);
    void update(Cliente cliente);
    void remove(Long id);
}
