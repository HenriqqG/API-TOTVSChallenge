package com.challenge.totvscrud.repository.rowmapper;

import com.challenge.totvscrud.entity.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementação de RowMapper para a entidade Cliente.
 */
public class ClienteRowMapper implements RowMapper<Cliente> {
    /**
     * Mapeia uma linha do ResultSet para um objeto Cliente.
     *
     * @param rs     O ResultSet contendo os dados da linha.
     * @param rowNum O número da linha no ResultSet.
     * @return O objeto Cliente mapeado a partir dos dados da linha do ResultSet.
     * @throws SQLException Se ocorrer algum erro ao acessar os dados do ResultSet.
     */
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