package add.commons.ibk.sample.service;

import add.commons.ibk.sample.dto.DeviceType;
import add.commons.ibk.sample.dto.HeaderDTO;
import add.commons.ibk.sample.exceptions.JsonException;
import add.commons.ibk.sample.model.Cliente;
import add.commons.ibk.sample.dto.ClienteDTO;
import add.commons.ibk.sample.repository.ClienteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Service
@Validated
@Transactional
/***
 * Servicio encargado de guardar y enviar evento del cliente
 * usando clienteRepository para el guardado
 * clienteEventHubService para el envio de eventos
 */
public class ClienteServiceImpl implements ClienteService {
    public static final String CORRECT_STATUS = "0000";
    public static final String NOT_EXIST_STATUS = "9999";
    Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private ClienteRepository clienteRepository;

    private Validator validator;

    private ClienteEventHubService clienteEventHubService;

    final ObjectMapper mapper;



    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteEventHubService clienteEventHubService, ObjectMapper mapper, Validator validator) {
        this.clienteRepository = clienteRepository;
        this.validator = validator;
        this.clienteEventHubService = clienteEventHubService;
        this.mapper = mapper;
    }


    public Cliente guardarCliente( Cliente cliente, Map<String, String> headers) throws JsonProcessingException, JsonException {
        Cliente receivedClient = cliente;
        cliente.setId(randomAlphabetic(10));
        StringBuilder sb = new StringBuilder();
        Errors errors = validator.validateObject(cliente);

        if (errors.hasErrors()) {
            for (ObjectError er : errors.getAllErrors()) {
                sb.append(er.getDefaultMessage()).append("\n");
            }
        }


        if (!sb.isEmpty()) {
            logger.error("ClienteService guardarCliente errores de validacion: {}", sb.toString());
            throw new IllegalArgumentException(sb.toString());
        }
        HeaderDTO headerDTO = HeaderParser.getHeadersParam(headers, sb);
        if (headerDTO != null) {
            Cliente response = clienteRepository.save(cliente);
            //sending eventhub
            String saveTransactionCode = System.getenv().getOrDefault("SAVETRANSACTIONCODE", "102");
            clienteEventHubService.sendEvent(headerDTO, cliente.getId(), CORRECT_STATUS, mapper.writeValueAsString(receivedClient), mapper.writeValueAsString(response), saveTransactionCode);
            logger.info("guardarCliente termino satisfactoriamente");
            return response;

        }

        return null;

    }

    public List<ClienteDTO> obtenerTodosClientes() {
        return (List<ClienteDTO>) clienteRepository.getAllClientesSortedByNombre();
    }

    public Cliente getClienteById(String id, Map<String, String> headers) throws JsonProcessingException, JsonException {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        logger.info("looking for cliente id {}", id);
        StringBuilder sb = new StringBuilder();
        HeaderDTO headerDTO = HeaderParser.getHeadersParam(headers, sb);
        String gettransactioncode = System.getenv().getOrDefault("GETTRANSACTIONCODE", "102");
        logger.info("cliente id exist {}", cliente.isPresent());
        if (cliente.isPresent()) {
            logger.info("cliente id {} exist in db", id);
            //enviar el evento con estado  existe
            clienteEventHubService.sendEvent(headerDTO, id, CORRECT_STATUS, id, mapper.writeValueAsString(cliente.get()), gettransactioncode);
            return cliente.get();
        }
        //enviar el evento con estado no existe
        clienteEventHubService.sendEvent(headerDTO, id, NOT_EXIST_STATUS, id, "", gettransactioncode);
        return null;
    }
}
