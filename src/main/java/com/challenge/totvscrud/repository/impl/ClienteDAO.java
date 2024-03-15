package com.challenge.totvscrud.repository.impl;

import com.challenge.totvscrud.entity.Cliente;
import com.challenge.totvscrud.repository.IClienteDAO;
import com.challenge.totvscrud.repository.rowmapper.ClienteRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteDAO implements IClienteDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Cliente> findAll() {
        try{
            StringBuilder sql = new StringBuilder();
            return jdbcTemplate.query(sql.toString(), new ClienteRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Cliente findById(Long id) {
        try{
            StringBuilder sql = new StringBuilder();
            return jdbcTemplate.queryForObject(sql.toString(), new ClienteRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new Cliente();
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Long insert(Cliente cliente) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TABLE_CLIENTE");
            sql.append("(NOME_CLIENTE, ");
            sql.append("CPF_CLIENTE, ");
            sql.append("ENDERECO_CLIENTE, ");
            sql.append("BAIRRO_CLIENTE) ");
            sql.append("VALUES(?,?,?,?) ");

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql.toString(), new String[] { "id" });

                ps.setString(1, cliente.getNomeCliente());
                ps.setString(2, cliente.getCpfCliente());
                ps.setString(3, cliente.getEnderecoCliente());
                ps.setString(4, cliente.getBairroCliente());
                return ps;

            }, keyHolder);

            return (Long) keyHolder.getKey();
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Não foi possível inserir novo Cliente", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(Cliente cliente) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TABLE_CLIENTE");
            sql.append("SET NOME_CLIENTE = ?, ");
            sql.append("CPF_CLIENTE = ?, ");
            sql.append("ENDERECO_CLIENTE = ?, ");
            sql.append("BAIRRO_CLIENTE = ? ");
            sql.append("WHERE ");
            sql.append("ID = ?");

            jdbcTemplate.update(sql.toString(),
                    cliente.getNomeCliente(),
                    cliente.getCpfCliente(),
                    cliente.getEnderecoCliente(),
                    cliente.getBairroCliente(),
                    cliente.getId());
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Não foi possível atualizar Cliente", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void remove(Long id) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("REMOVE FROM TABLE_CLIENTE");
            sql.append("WHERE ");
            sql.append("ID = ?");

            jdbcTemplate.update(sql.toString(),id);
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Não foi possível remover Cliente", e);
        }
    }
}
