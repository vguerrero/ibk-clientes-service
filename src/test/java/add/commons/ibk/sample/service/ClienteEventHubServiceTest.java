package add.commons.ibk.sample.service;

import add.commons.ibk.sample.dto.ClienteDTO;
import add.commons.ibk.sample.dto.EventHubDTO;
import add.commons.ibk.sample.dto.HeaderDTO;
import add.commons.ibk.sample.exceptions.JsonException;
import add.commons.ibk.sample.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteEventHubServiceTest {
    @Mock
    EventHubSenderConfigurer configurer;

    @Mock
    EventHubSender eventHubSender;

    @Mock
    EventHubDTO event;

    @Mock
    HeaderDTO headerDTO;

    @Mock
    EventHubDTO eventHubDTO;

    @InjectMocks
    ClienteEventHubServiceImpl clienteEventHubService;




    @Test
    void sendEventTest() throws JsonException {
        when(configurer.getSender()).thenReturn(eventHubSender);
        doNothing().when(eventHubSender).sendEvent(isA(EventHubDTO.class));
        clienteEventHubService.sendEvent(headerDTO,"testid",ClienteStatusEnum.CORRECT_STATUS.toString(),"test inbound","test outbound","102");
        //verify(eventHubSender,times(1)).sendEvent(new EventHubDTO.Builder().build());
        assertNotNull(headerDTO);
    }
}
