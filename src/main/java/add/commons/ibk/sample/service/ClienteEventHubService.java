package add.commons.ibk.sample.service;

import add.commons.ibk.sample.dto.HeaderDTO;
import add.commons.ibk.sample.exceptions.JsonException;

public interface ClienteEventHubService {
    void sendEvent(HeaderDTO headerDTO, String customerId, String statusCode, String inbound, String outbound, String transactionCode ) throws JsonException;
}
