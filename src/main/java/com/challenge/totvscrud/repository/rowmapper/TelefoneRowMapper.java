package com.challenge.totvscrud.repository.rowmapper;

import com.challenge.totvscrud.entity.Cliente;
import com.challenge.totvscrud.entity.Telefone;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TelefoneRowMapper implements RowMapper<Telefone> {
    @Override
    public Telefone mapRow(ResultSet rs, int rowNum){
        Telefone telefone = new Telefone();
        return telefone;
    }
}
