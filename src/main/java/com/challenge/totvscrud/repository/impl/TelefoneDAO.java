package com.challenge.totvscrud.repository.impl;

import com.challenge.totvscrud.entity.Telefone;
import com.challenge.totvscrud.repository.ITelefoneDAO;
import com.challenge.totvscrud.repository.rowmapper.TelefoneRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface ITelefoneDAO que realiza operações de acesso a dados para entidade Telefone.
 */
@Repository
@AllArgsConstructor
public class TelefoneDAO implements ITelefoneDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Recupera todos os telefones associados a um cliente pelo ID do cliente.
     *
     * @param idCliente O ID do cliente.
     * @return Lista contendo todos os telefones associados ao cliente.
     */
    @Override
    public List<Telefone> findAllByClienteId(Long idCliente) {
        try{
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT  ");
            sql.append("ID_TELEFONE, ");
            sql.append("NUMR_TELEFONE, ");
            sql.append("ID_CLIENTE ");
            sql.append("FROM TABLE_TELEFONE ");
            sql.append("WHERE ");
            sql.append("1 = 1 ");
            sql.append("AND ID_CLIENTE = ").append(idCliente);

            return jdbcTemplate.query(sql.toString(), new TelefoneRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    /**
     * Insere um novo telefone na base de dados.
     *
     * @param telefone O telefone a ser inserido.
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void insert(Telefone telefone) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO TABLE_TELEFONE ");
            sql.append("(NUMR_TELEFONE, ");
            sql.append("ID_CLIENTE) ");
            sql.append("VALUES(?,?) ");

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection
                        .prepareStatement(sql.toString(), new String[] { "ID_TELEFONE" });

                ps.setString(1, telefone.getNumeroTelefone());
                ps.setLong(2, telefone.getIdCliente());
                return ps;

            }, keyHolder);
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Não foi possível inserir novo Telefone", e);
        }
    }

    /**
     * Remove um telefone da base de dados pelo seu ID.
     *
     * @param id O ID do telefone a ser removido.
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void remove(Long id) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TABLE_TELEFONE ");
            sql.append("WHERE ");
            sql.append("ID_TELEFONE = ?");

            jdbcTemplate.update(sql.toString(),id);
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Não foi possível remover Telefone", e);
        }
    }
}
