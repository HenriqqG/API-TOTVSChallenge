package com.challenge.totvscrud.repository.rowmapper;

import com.challenge.totvscrud.entity.Telefone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementação de RowMapper para a entidade Telefone.
 */
public class TelefoneRowMapper implements RowMapper<Telefone> {
    /**
     * Mapeia uma linha do ResultSet para um objeto Telefone.
     *
     * @param rs     O ResultSet contendo os dados da linha.
     * @param rowNum O número da linha no ResultSet.
     * @return O objeto Telefone mapeado a partir dos dados da linha do ResultSet.
     * @throws SQLException Se ocorrer algum erro ao acessar os dados do ResultSet.
     */
    @Override
    public Telefone mapRow(ResultSet rs, int rowNum) throws SQLException {
        Telefone telefone = new Telefone();

        telefone.setId(rs.getLong("ID_TELEFONE"));
        telefone.setNumeroTelefone(rs.getString("NUMR_TELEFONE"));
        telefone.setIdCliente(rs.getLong("ID_CLIENTE"));

        return telefone;
    }
}
