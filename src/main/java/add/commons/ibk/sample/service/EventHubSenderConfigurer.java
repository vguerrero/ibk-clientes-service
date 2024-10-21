package add.commons.ibk.sample.service;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerAsyncClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventHubSenderConfigurer {

    @Value("${azure.eventhub.connectionString}")
    private  String connectionString;

    @Value("${azure.eventhub.eventHubName}")
    private  String eventHubName;


    private EventHubSender sender;

    public  EventHubSender getSender() {
            if(connectionString != null && eventHubName != null){

               try( EventHubProducerAsyncClient producer = new EventHubClientBuilder().connectionString(connectionString)
                        .eventHubName(eventHubName).buildAsyncProducerClient()){
                   sender = new EventHubSender(producer,new ObjectMapper());
               }
            }

        return sender;
    }

}
