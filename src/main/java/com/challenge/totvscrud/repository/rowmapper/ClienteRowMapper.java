package com.challenge.totvscrud.repository.rowmapper;

import com.challenge.totvscrud.entity.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {
    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cliente cliente = new Cliente();

        cliente.setId(rs.getLong("ID_CLIENTE"));
        cliente.setNomeCliente(rs.getString("NOME_CLIENTE"));
        cliente.setCpfCliente(rs.getString("CPF_CLIENTE"));
        cliente.setEnderecoCliente(rs.getString("ENDERECO_CLIENTE"));
        cliente.setBairroCliente(rs.getString("BAIRRO_CLIENTE"));

        return cliente;
    }
}