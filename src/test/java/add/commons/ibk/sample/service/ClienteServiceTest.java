package add.commons.ibk.sample.service;

import add.commons.ibk.sample.dto.ClienteDTO;
import add.commons.ibk.sample.exceptions.JsonException;
import add.commons.ibk.sample.model.Cliente;
import add.commons.ibk.sample.repository.ClienteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.*;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    public static final String EL_CLIENTE_LE_FALTA_EL_NOMBRE = "falta el dato";
    @Mock
    ClienteRepository clienteRepository;

    @Mock
    ClienteEventHubService clienteEventHubService;

    @Mock
    ObjectMapper mapper;

    @InjectMocks
    ClienteServiceImpl clienteService;

    @Mock
    Validator validator;

    Cliente cliente;
    Map<String, String> headers;
    ClienteDTO clienteDTO;

    @BeforeEach
    void config() {
        this.cliente = new Cliente("", "mateo", "lopez", "duque", new Date(), true);
        this.clienteDTO = new ClienteDTO("testid", "mateo lopez");
        this.headers = new HashMap<>();
        headers.put("consumerid", "SMP");
        headers.put("traceparent", "00-db65adadcc7ab67b6eaa38521c34c42a-c1d2e415b56c412b-01");
        headers.put("devicetype", "AND");
        headers.put("deviceid", "MA42FJ799HF");
    }

    @Test
    void guardarCliente() throws JsonException, JsonProcessingException {
        Cliente response = cliente;
        when(clienteRepository.save(this.cliente)).thenReturn(response);
        Errors errors = mock(Errors.class);
        when(validator.validateObject(this.cliente)).thenReturn(errors);
        Cliente clienteg = clienteService.guardarCliente(cliente, headers);
        assertNotNull(clienteg.getId());
        assertEquals("mateo", clienteg.getNombre());
    }

    @Test
    void guardarClienteValidationFails() throws JsonException, JsonProcessingException {
        Cliente mycliente = new Cliente("", "", "lopez", "duque", new Date(), true);
        Errors errors = mock(Errors.class);
        ObjectError objectError = mock(ObjectError.class);
        List<Errors> mockErrors = Collections.singletonList(errors);
        when(objectError.getDefaultMessage()).thenReturn(EL_CLIENTE_LE_FALTA_EL_NOMBRE);
        when(errors.hasErrors()).thenReturn(true);
        when(errors.getAllErrors()).thenReturn(Collections.singletonList(objectError));
        when(validator.validateObject(mycliente)).thenReturn(errors);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clienteService.guardarCliente(mycliente, headers);
        });
        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals(EL_CLIENTE_LE_FALTA_EL_NOMBRE + "\n", exception.getMessage());
    }

    @Test
    void clienteByIdTest() throws JsonException, JsonProcessingException {
        String id = "abcdefg";
        when(clienteRepository.findById(id)).thenReturn(Optional.of(this.cliente));
        Cliente clienteResp = clienteService.getClienteById(id, this.headers);
        assertNotNull(clienteResp.getId());
        assertEquals("mateo", clienteResp.getNombre());
    }

    @Test
    void clienteGetAllTest() {
        String id = "abcdefg";
        when(clienteRepository.getAllClientesSortedByNombre()).thenReturn(Collections.singletonList(clienteDTO));
        List<ClienteDTO> clienteResp = clienteService.obtenerTodosClientes();
        assertNotNull(clienteResp);
        assertEquals(1, clienteResp.size());
    }

    @Test
    void clienteByIdNotFoundTest() throws JsonException, JsonProcessingException {
        String id = "abcdefg";
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());
        Cliente clienteResp = clienteService.getClienteById(id, this.headers);
        assertNull(clienteResp);
    }
}
