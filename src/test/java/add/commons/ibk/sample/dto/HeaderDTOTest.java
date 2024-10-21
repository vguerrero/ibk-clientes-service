package add.commons.ibk.sample.dto;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HeaderDTOTest {

    @Test
    void coverHeaderDto(){
        HashMap<String,String> headers = new HashMap<>();
        headers.put("consumerid", "SMP");
        headers.put("traceparent", "00-db65adadcc7ab67b6eaa38521c34c42a-c1d2e415b56c412b-01");
        headers.put("devicetype", "AND");
        headers.put("deviceid", "MA42FJ799HF");
        HeaderDTO headerDTO = new HeaderDTO(headers.get("consumerid"), headers.get("traceparent"), headers.get("devicetype"), headers.get("deviceid"));
        assertNotNull(headerDTO.getTraceparent());
        assertNotNull(headerDTO.getDeviceType());
        assertNotNull(headerDTO.getDeviceId());
        headerDTO.setTraceparent("newTrace");
        headerDTO.setDeviceType("newDevice");
        headerDTO.setDeviceType("setDeviceId");
        assertEquals("setDeviceId", headerDTO.getDeviceType());



    }
}
