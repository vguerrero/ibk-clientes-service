package add.commons.ibk.sample.controllers;

import add.commons.ibk.sample.exceptions.JsonException;
import add.commons.ibk.sample.model.Cliente;
import add.commons.ibk.sample.dto.ClienteDTO;
import add.commons.ibk.sample.service.ClienteServiceImpl;
import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClientController {


    Logger logger = LoggerFactory.getLogger(ClientController.class);
    private ClienteServiceImpl clienteService;

    public ClientController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> saveClientes( @RequestBody Cliente cliente, @RequestHeader Map<String, String> headers) {
        try {
            Cliente cliente1 = clienteService.guardarCliente(cliente, headers);
            return new ResponseEntity<>(cliente1, HttpStatusCode.valueOf(200));
        }
        catch (IllegalArgumentException il) {
            return new ResponseEntity<>(il.getMessage(), HttpStatusCode.valueOf(400));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getClientes() {
        return ResponseEntity.of(Optional.ofNullable(clienteService.obtenerTodosClientes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable String id, @RequestHeader Map<String, String> headers) {
        try {
            Cliente cliente = clienteService.getClienteById(id, headers);
            if (cliente != null)
                return new ResponseEntity<>(cliente, HttpStatusCode.valueOf(200));
            return new ResponseEntity<>("el cliente no se encuentra en el sistema", HttpStatusCode.valueOf(404));

        } catch (JsonException | JsonProcessingException e) {
            return new ResponseEntity<>("hubo un error al momento de obtener la informacion", HttpStatusCode.valueOf(500));
        }

    }

    @GetMapping("/test")
    public String getEnvs() {
        String region = System.getenv().getOrDefault("REGION", "RegionDefecto");
        String getTransactionCode = System.getenv().getOrDefault("GETTRANSACTIONCODE", "gettranDefecto");
        String saveTransactionCode = System.getenv().getOrDefault("SAVETRANSACTIONCODE", "saveDefecto");
        return " " + region + " " + saveTransactionCode + " " + getTransactionCode;
    }

}
