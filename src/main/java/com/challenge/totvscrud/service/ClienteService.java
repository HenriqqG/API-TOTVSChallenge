package com.challenge.totvscrud.service;

import com.challenge.totvscrud.commons.CPFUtil;
import com.challenge.totvscrud.commons.TelefoneUtil;
import com.challenge.totvscrud.entity.Cliente;
import com.challenge.totvscrud.entity.Telefone;
import com.challenge.totvscrud.entity.dto.ClienteDTO;
import com.challenge.totvscrud.repository.IClienteDAO;
import com.challenge.totvscrud.repository.ITelefoneDAO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço responsável por realizar operações relacionadas à entidade Cliente.
 */
@Service
@AllArgsConstructor
public class ClienteService {

    private final IClienteDAO clienteRepository;
    private final ITelefoneDAO telefoneRepository;
    private static final String CLIENTE_NOT_FOUND = "Não foi possível encontrar Cliente com id %d";

    /**
     * Recupera todos os clientes.
     *
     * @return Lista contendo todos os clientes.
     */
    public List<ClienteDTO> findAll(){
        return clienteRepository.findAll().stream()
                .map(Cliente::ToDTO).toList();
    }

    /**
     * Recupera um cliente pelo seu ID.
     *
     * @param id O ID do cliente.
     * @return O objeto ClienteDTO correspondente ao cliente encontrado.
     * @throws ResponseStatusException Se o cliente não for encontrado.
     */
    public ClienteDTO findById(Long id){
        Cliente cliente = clienteRepository.findById(id);
        if(ObjectUtils.isEmpty(cliente)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CLIENTE_NOT_FOUND, id));
        }
        return cliente.ToDTO();
    }

    /**
     * Insere um novo cliente na base de dados.
     *
     * @param clienteDTO O objeto ClienteDTO contendo os dados do cliente a ser inserido.
     */
    public void inserirNovoCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente(clienteDTO);

        List<Telefone> telefones = new ArrayList<>();
        clienteDTO.telefones().forEach(telefoneDTO -> {
            Telefone telefone = new Telefone(telefoneDTO.numero(), telefoneDTO.idCliente());
            telefones.add(telefone);
        });
        cliente.setTelefones(telefones);

        validaCliente(cliente);

        Long idCliente = clienteRepository.insert(cliente);
        clienteDTO.telefones().forEach(tel -> {
            Telefone telefone = new Telefone(tel.numero(), idCliente);
            telefoneRepository.insert(telefone);
        });
    }

    /**
     * Altera os dados de um cliente existente na base de dados.
     *
     * @param id         O ID do cliente a ser alterado.
     * @param clienteDTO O objeto ClienteDTO contendo os novos dados do cliente.
     */
    public void alterarCliente(Long id, ClienteDTO clienteDTO){
        Cliente clienteToUpdate = clienteRepository.findById(id);
        if(ObjectUtils.isEmpty(clienteToUpdate)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CLIENTE_NOT_FOUND, id));
        }

        clienteToUpdate.setNomeCliente(ObjectUtils.isEmpty(clienteDTO.nome()) ? clienteToUpdate.getNomeCliente() : clienteDTO.nome());
        clienteToUpdate.setCpfCliente(ObjectUtils.isEmpty(clienteDTO.cpf()) ? clienteToUpdate.getCpfCliente() : clienteDTO.cpf());
        clienteToUpdate.setBairroCliente(ObjectUtils.isEmpty(clienteDTO.bairro()) ? clienteToUpdate.getBairroCliente() : clienteDTO.bairro());
        clienteToUpdate.setEnderecoCliente(ObjectUtils.isEmpty(clienteDTO.endereco()) ? clienteToUpdate.getEnderecoCliente() : clienteDTO.endereco());
        validaCliente(clienteToUpdate);

        clienteRepository.update(clienteToUpdate);
    }

    /**
     * Remove um cliente da base de dados.
     *
     * @param id O ID do cliente a ser removido.
     */
    public void removerCliente(Long id){
        Cliente cliente = clienteRepository.findById(id);
        if(ObjectUtils.isEmpty(cliente)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format(CLIENTE_NOT_FOUND, id));
        }

        cliente.getTelefones().forEach(tel -> telefoneRepository.remove(tel.getId()));
        clienteRepository.remove(cliente.getId());
    }

    /**
     * Valida os dados de um cliente.
     *
     * @param cliente O objeto Cliente a ser validado.
     * @throws IllegalArgumentException Se o CPF ou os telefones do cliente forem inválidos.
     */
    private void validaCliente(Cliente cliente) {
        if(!CPFUtil.isCPF(cliente.getCpfCliente())){
            throw new IllegalArgumentException("Número de CPF inválido.");
        }
        cliente.getTelefones().forEach(tel -> TelefoneUtil.isTelefoneValido(tel.getNumeroTelefone()));
    }
}
