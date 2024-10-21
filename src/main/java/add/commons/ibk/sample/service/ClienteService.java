package add.commons.ibk.sample.service;

import add.commons.ibk.sample.exceptions.JsonException;
import add.commons.ibk.sample.model.Cliente;
import add.commons.ibk.sample.dto.ClienteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface ClienteService {
    Cliente guardarCliente( Cliente cliente, Map<String, String> headers) throws JsonProcessingException, JsonException;
    List<ClienteDTO> obtenerTodosClientes();
}
