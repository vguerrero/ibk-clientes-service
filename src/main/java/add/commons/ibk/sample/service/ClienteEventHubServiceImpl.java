package add.commons.ibk.sample.service;

import add.commons.ibk.sample.dto.EventHubDTO;
import add.commons.ibk.sample.dto.HeaderDTO;
import add.commons.ibk.sample.exceptions.JsonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Service
public class ClienteEventHubServiceImpl implements ClienteEventHubService {

    private EventHubSenderConfigurer configurer;

    public ClienteEventHubServiceImpl(EventHubSenderConfigurer configurer) {
        this.configurer = configurer;
    }

    Logger logger = LoggerFactory.getLogger(ClienteEventHubServiceImpl.class);

    public void sendEvent(HeaderDTO headerDTO,String customerId, String statusCode, String inbound, String outbound, String transactionCode ) throws JsonException {
        if(headerDTO != null){
            String region = System.getenv().getOrDefault("REGION", "EAST");
            EventHubDTO event = new EventHubDTO.Builder().
                    analyticsTraceSource("application-"+headerDTO.getConsumerId())
                    .applicationId(headerDTO.getConsumerId())
                    .channelOperationNumber(randomNumeric(13))
                    .region(region)
                    .currentDate(new Date())
                    .customerId(customerId)
                    .statusCode(statusCode)
                    .timestamp(String.valueOf(System.currentTimeMillis()))
                    .traceId(randomAlphabetic(32))
                    .inbound(inbound)
                    .outbound(outbound)
                    .transactionCode(transactionCode)
                    .build();

            EventHubSender eventHubSender =  configurer.getSender();
            if(eventHubSender != null){
                logger.info("sending cliente event {}",event);
                eventHubSender.sendEvent(event);
            }

        }
    }
}
