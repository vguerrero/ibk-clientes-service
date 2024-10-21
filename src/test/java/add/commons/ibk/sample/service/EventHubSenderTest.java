package add.commons.ibk.sample.service;

import add.commons.ibk.sample.dto.EventHubDTO;
import add.commons.ibk.sample.exceptions.JsonException;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventHubSenderTest {

    @Mock
    EventHubProducerAsyncClient producer;
    @Mock
    ObjectMapper objectMapper;

    @Mock
    EventHubDTO event;

    @Mock
    EventData eventData;

    @Mock
    Mono<Void> mono;

    @InjectMocks
    EventHubSender eventHubSender;

    @Test
    void sendEventTest() throws JsonException, JsonProcessingException {
        var coll = Collections.singletonList(eventData);

        String jsonEvent="{name:'hercules'}";
        byte[] arrayByte = jsonEvent.getBytes();
        when(objectMapper.writeValueAsString(any(Object.class))).thenReturn(jsonEvent);
        when(objectMapper.writeValueAsBytes(any(Object.class))).thenReturn(arrayByte);
        when(producer.send(any(Iterable.class))).thenReturn(mono);
        eventHubSender.sendEvent(event);
        //assertNotNull(producer);
    }


}
