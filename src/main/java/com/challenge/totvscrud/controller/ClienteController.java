package com.challenge.totvscrud.controller;

import com.challenge.totvscrud.entity.dto.ClienteDTO;
import com.challenge.totvscrud.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações relacionadas aos clientes.
 */
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes",
        description = "Operações relacionadas aos Clientes")
public class ClienteController {

    private final ClienteService clienteService;

    /**
     * Lista todos os clientes.
     *
     * @return Uma lista de clientes em formato JSON.
     */
    @Operation(summary = "Listar todos os clientes",
            description = "Retorna uma lista de todos os clientes cadastrados.")
    @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class))))
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes(){
        List<ClienteDTO> clienteDTOList = clienteService.findAll();
        return new ResponseEntity<>(clienteDTOList, HttpStatus.OK);
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param id O ID do cliente a ser buscado.
     * @return O cliente encontrado em formato JSON.
     */
    @Operation(summary = "Buscar cliente pelo ID",
            description = "Retorna o cliente correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ok",
                    content = @Content(schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Cliente não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id){
        ClienteDTO clienteDTOResponse = clienteService.findById(id);
        return new ResponseEntity<>(clienteDTOResponse, HttpStatus.OK);
    }

    /**
     * Insere um novo cliente.
     *
     * @param clienteDTO Os dados do cliente a serem inseridos.
     * @return Uma resposta HTTP indicando o sucesso da operação.
     */
    @Operation(summary = "Inserir novo cliente",
            description = "Insere um novo cliente.")
    @ApiResponse(responseCode = "201",
            description = "Created")
    @PostMapping
    public ResponseEntity<HttpStatus> inserirNovoCliente(@RequestBody ClienteDTO clienteDTO){
        clienteService.inserirNovoCliente(clienteDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Altera os dados de um cliente.
     *
     * @param id         O ID do cliente a ser alterado.
     * @param clienteDTO Os novos dados do cliente.
     * @return Uma resposta HTTP indicando o sucesso da operação.
     */
    @Operation(summary = "Alterar dados do cliente",
            description = "Altera os dados de um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ok"),
            @ApiResponse(responseCode = "404",
                    description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> alterarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        clienteService.alterarCliente(id, clienteDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Remove um cliente.
     *
     * @param id O ID do cliente a ser removido.
     * @return Uma resposta HTTP indicando o sucesso da operação.
     */
    @Operation(summary = "Remover cliente",
            description = "Remove um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Ok"),
            @ApiResponse(responseCode = "404",
                    description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removerCliente(@PathVariable Long id){
        clienteService.removerCliente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
