package add.commons.ibk.sample.dto;

public class HeaderDTO {
    private String consumerId;
    private String traceparent;
    private String deviceType;
    private String deviceId;

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getTraceparent() {
        return traceparent;
    }

    public void setTraceparent(String traceparent) {
        this.traceparent = traceparent;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public HeaderDTO(String consumerId, String traceparent, String deviceType, String deviceId) {
        this.consumerId = consumerId;
        this.traceparent = traceparent;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
    }
}
