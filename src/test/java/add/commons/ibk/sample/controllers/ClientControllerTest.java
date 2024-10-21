package add.commons.ibk.sample.controllers;

import add.commons.ibk.sample.exceptions.JsonException;
import add.commons.ibk.sample.model.Cliente;
import add.commons.ibk.sample.service.ClienteServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ClienteServiceImpl clienteService;

    Cliente cliente;
    Map<String, String> headers;


    @BeforeEach
    void config() {
        this.cliente = new Cliente("", "mateo", "lopez", "duque", new Date(), true);
        this.headers = new HashMap<>();
        headers.put("consumerid", "SMP");
        headers.put("traceparent", "00-db65adadcc7ab67b6eaa38521c34c42a-c1d2e415b56c412b-01");
        headers.put("devicetype", "AND");
        headers.put("deviceid", "MA42FJ799HF");

    }

    @Test
    void saveClienteTest() throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(this.headers);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(ClientControllerTest.asJsonString( this.cliente ))
                                .headers(httpHeaders)
                )

                .andExpect(status().isOk());


    }
    @Test
    void saveClienteFailTest() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(this.headers);
        when(clienteService.guardarCliente(any(Cliente.class),any(Map.class))).thenThrow(new JsonException("Error de parseo json"));
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(ClientControllerTest.asJsonString( this.cliente ))
                                .headers(httpHeaders)
                )

                .andExpect(status().is5xxServerError());
    }

    @Test
    void getClienteByIdTest() throws Exception {
        String id ="testobjId";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(this.headers);
        when(clienteService.getClienteById(any(String.class),any(Map.class))).thenReturn(this.cliente);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/clientes/"+id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(ClientControllerTest.asJsonString( this.cliente ))
                                .headers(httpHeaders)
                )

                .andExpect(status().isOk());


    }

    @Test
    void getClienteByIdClientNullTest() throws Exception {
        String id ="testobjId";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(this.headers);
        when(clienteService.getClienteById(any(String.class),any(Map.class))).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/clientes/"+id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(ClientControllerTest.asJsonString( this.cliente ))
                                .headers(httpHeaders)
                )

                .andExpect(status().isNotFound());


    }

    @Test
    void getClienteByIdThrowErrorTest() throws Exception {
        String id ="testobjId";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(this.headers);
        when(clienteService.getClienteById(any(String.class),any(Map.class))).thenThrow(new JsonException("error json"));
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/clientes/"+id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(ClientControllerTest.asJsonString( this.cliente ))
                                .headers(httpHeaders)
                )

                .andExpect(status().is5xxServerError());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
