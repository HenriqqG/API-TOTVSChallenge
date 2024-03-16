package com.challenge.totvscrud.repository.impl;

import com.challenge.totvscrud.entity.Cliente;
import com.challenge.totvscrud.entity.Telefone;
import com.challenge.totvscrud.repository.IClienteDAO;
import com.challenge.totvscrud.repository.rowmapper.ClienteRowMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementação da interface IClienteDAO que realiza operações de acesso a dados para entidade Cliente.
 */
@Repository
@AllArgsConstructor
public class ClienteDAO implements IClienteDAO {

    private final JdbcTemplate jdbcTemplate;
    private final TelefoneDAO telefoneRepository;

    /**
     * Recupera todos os clientes da base de dados.
     *
     * @return Lista contendo todos os clientes cadastrados.
     */
    @Override
    public List<Cliente> findAll() {
        try{
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT  ");
            sql.append("ID_CLIENTE, ");
            sql.append("NOME_CLIENTE, ");
            sql.append("CPF_CLIENTE, ");
            sql.append("ENDERECO_CLIENTE, ");
            sql.append("BAIRRO_CLIENTE ");
            sql.append("FROM TABLE_CLIENTE");

            List<Cliente> clienteList = jdbcTemplate.query(sql.toString(), new ClienteRowMapper());
            for(Cliente cl : clienteList){
                List<Telefone> telefoneList = telefoneRepository.findAllByClienteId(cl.getId());
                cl.setTelefones(telefoneList);
            }

            return clienteList;
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }

    /**
     * Recupera um cliente da base de dados pelo seu ID.
     *
     * @param id O ID do cliente a ser buscado.
     * @return O cliente encontrado, ou um objeto vazio se não houver correspondência.
     */
    @Override
    public Cliente findById(Long id) {
        try{
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT  ");
            sql.append("ID_CLIENTE, ");
            sql.append("NOME_CLIENTE, ");
            sql.append("CPF_CLIENTE, ");
            sql.append("ENDERECO_CLIENTE, ");
            sql.append("BAIRRO_CLIENTE ");
            sql.append("FROM TABLE_CLIENTE ");
            sql.append("WHERE ");
            sql.append("1 = 1 ");
            sql.append("AND ID_CLIENTE = ").append(id);

            Cliente cliente = jdbcTemplate.queryForObject(sql.toString(), new ClienteRowMapper());
            if(!ObjectUtils.isEmpty(cliente)){
                List<Telefone> telefoneList = telefoneRepository.findAllByClienteId(cliente.getId());
                cliente.setTelefones(telefoneList);
            }

            return cliente;
        }catch (EmptyResultDataAccessException e){
            return new Cliente();
        }
    }

    /**
     * Insere um novo cliente na base de dados.
     *
     * @param cliente O cliente a ser inserido.
     * @return O ID do cliente inserido.
     */
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
                        .prepareStatement(sql.toString(), new String[] { "ID_CLIENTE" });

                ps.setString(1, cliente.getNomeCliente());
                ps.setString(2, cliente.getCpfCliente());
                ps.setString(3, cliente.getEnderecoCliente());
                ps.setString(4, cliente.getBairroCliente());
                return ps;
            }, keyHolder);

            return Objects.requireNonNull(keyHolder.getKey()).longValue();
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Não foi possível inserir novo Cliente", e);
        }
    }

    /**
     * Atualiza um cliente na base de dados.
     *
     * @param cliente O cliente a ser atualizado.
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(Cliente cliente) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE TABLE_CLIENTE ");
            sql.append("SET NOME_CLIENTE = ?, ");
            sql.append("CPF_CLIENTE = ?, ");
            sql.append("ENDERECO_CLIENTE = ?, ");
            sql.append("BAIRRO_CLIENTE = ? ");
            sql.append("WHERE ");
            sql.append("ID_CLIENTE = ?");

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

    /**
     * Remove um cliente da base de dados pelo seu ID.
     *
     * @param id O ID do cliente a ser removido.
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void remove(Long id) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM TABLE_CLIENTE ");
            sql.append("WHERE ");
            sql.append("ID_CLIENTE = ?");

            jdbcTemplate.update(sql.toString(),id);
        }catch (EmptyResultDataAccessException e){
            throw new RuntimeException("Não foi possível remover Cliente", e);
        }
    }
}
