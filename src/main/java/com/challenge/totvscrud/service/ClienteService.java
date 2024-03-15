package com.challenge.totvscrud.service;

import com.challenge.totvscrud.commons.CPFUtil;
import com.challenge.totvscrud.commons.TelefoneUtil;
import com.challenge.totvscrud.entity.Cliente;
import com.challenge.totvscrud.entity.Telefone;
import com.challenge.totvscrud.entity.dto.ClienteDTO;
import com.challenge.totvscrud.entity.dto.TelefoneDTO;
import com.challenge.totvscrud.repository.IClienteDAO;
import com.challenge.totvscrud.repository.ITelefoneDAO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private final IClienteDAO clienteRepository;
    private final ITelefoneDAO telefoneRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(ClienteDTO clienteDTO){
        return clienteRepository.findById(clienteDTO.id());
    }

    public void inserirNovoCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente(clienteDTO);
        validaCliente(cliente);

        Long idCliente = clienteRepository.insert(cliente);
        clienteDTO.telefones().forEach(tel -> {
            Telefone telefone = new Telefone(tel.numero(), idCliente);
            telefoneRepository.insert(telefone);
        });
    }

    public void alterarCliente(ClienteDTO clienteDTO){
        Cliente clienteToUpdate = clienteRepository.findById(clienteDTO.id());
        if(ObjectUtils.isEmpty(clienteToUpdate)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Não foi possível encontrar Cliente com id %d", clienteDTO.id()));
        }

        clienteToUpdate.setNomeCliente(ObjectUtils.isEmpty(clienteDTO.nome()) ? clienteToUpdate.getNomeCliente() : clienteDTO.nome());
        clienteToUpdate.setCpfCliente(ObjectUtils.isEmpty(clienteDTO.cpf()) ? clienteToUpdate.getCpfCliente() : clienteDTO.cpf());
        clienteToUpdate.setBairroCliente(ObjectUtils.isEmpty(clienteDTO.bairro()) ? clienteToUpdate.getBairroCliente() : clienteDTO.bairro());
        clienteToUpdate.setEnderecoCliente(ObjectUtils.isEmpty(clienteDTO.endereco()) ? clienteToUpdate.getEnderecoCliente() : clienteDTO.endereco());
        validaCliente(clienteToUpdate);

        clienteRepository.update(clienteToUpdate);
    }

    private void removerCliente(ClienteDTO clienteDTO){
        clienteDTO.telefones().forEach(tel -> telefoneRepository.remove(tel.id()));
        clienteRepository.remove(clienteDTO.id());
    }

    private void validaCliente(Cliente cliente) {
        if(CPFUtil.isCPF(cliente.getCpfCliente())){
            throw new IllegalArgumentException("Número de CPF inválido.");
        }
        cliente.getTelefones().forEach(tel -> TelefoneUtil.isTelefoneValido(tel.getNumeroTelefone()));
    }
}
