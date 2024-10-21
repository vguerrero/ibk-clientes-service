package add.commons.ibk.sample.service;

import add.commons.ibk.sample.dto.EventHubDTO;
import add.commons.ibk.sample.exceptions.JsonException;
import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class EventHubSender {

    private EventHubProducerAsyncClient producer;
    private final Logger logger = LoggerFactory.getLogger(EventHubSender.class);
    ObjectMapper objectMapper;

    public EventHubSender(EventHubProducerAsyncClient producer, ObjectMapper objectMapper) {
        this.producer = producer;
        if(objectMapper != null){
            this.objectMapper=objectMapper;
        }
        else{
            this.objectMapper=new ObjectMapper();
        }
    }

    public void sendEvent(EventHubDTO event) throws JsonException {
        List<EventData> allEvents = Collections.singletonList(new EventData(messageToBytes(event)));
        producer.send(allEvents);
    }

    private byte[] messageToBytes(EventHubDTO event) throws JsonException {
        String jsonEvent = null;
        try {
            jsonEvent = objectMapper.writeValueAsString(event);
            logger.info("objectToBytes jsonObject {}", jsonEvent);
            return objectMapper.writeValueAsBytes(jsonEvent);
        } catch (JsonProcessingException e) {
            throw new JsonException("Error al parsear el objeto json para event hub");
        }


    }
}
