package com.challenge.totvscrud.repository;

import com.challenge.totvscrud.entity.Telefone;

import java.util.List;

public interface ITelefoneDAO {
    List<Telefone> findAll();
    Telefone findById(Long id);
    Long insert(Telefone telefone);
    void update(Telefone telefone);
    void remove(Long id);
}
