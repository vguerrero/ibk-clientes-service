package add.commons.ibk.sample.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestPropertySource(properties = { "azure.eventhub.connectionString=mockConString" ,"azure.eventhub.eventHubName=mockEventHubName"})
@ExtendWith(MockitoExtension.class)
public class EventHubSenderConfigurerTest {


    @Mock
    EventHubSender sender;

    @InjectMocks
    EventHubSenderConfigurer configurer;

    @Test
    void getTestSender(){
        var obj = configurer.getSender();
        assertNotNull(obj);
    }
}
