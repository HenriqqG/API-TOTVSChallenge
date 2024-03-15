package com.challenge.totvscrud.repository.rowmapper;

import com.challenge.totvscrud.entity.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {
    @Override
    public Cliente mapRow(ResultSet rs, int rowNum){
        Cliente cliente = new Cliente();
        return cliente;
    }
}