package add.commons.ibk.sample.service;

import add.commons.ibk.sample.dto.HeaderDTO;

import java.util.Map;

public class HeaderParser {

    public static HeaderDTO getHeadersParam(Map<String, String> header, StringBuilder sb) {
        HeaderDTO headerDTO = null;
        if (!header.isEmpty()) {
            headerDTO = new HeaderDTO(header.get("consumerid"), header.get("traceparent"), header.get("devicetype"), header.get("deviceid"));
        } else {
            sb.append("los headers de la solicitud estan vacios");
        }
        return headerDTO;
    }
}
