package add.commons.ibk.sample.dto;

import java.io.Serializable;
import java.util.Date;

public class EventHubDTO  implements Serializable  {
    private String analyticsTraceSource;
    private String applicationId;
    private String channelOperationNumber;
    private String consumerId;
    private Date currentDate;
    private String customerId;
    private String region;
    private String statusCode;
    private String timestamp;
    private String traceId;
    private String inbound;
    private String outbound;
    private String transactionCode;

    public String getAnalyticsTraceSource() {
        return analyticsTraceSource;
    }


    public String getApplicationId() {
        return applicationId;
    }


    public String getChannelOperationNumber() {
        return channelOperationNumber;
    }


    public String getConsumerId() {
        return consumerId;
    }


    public Date getCurrentDate() {
        return currentDate;
    }


    public String getCustomerId() {
        return customerId;
    }


    public String getRegion() {
        return region;
    }


    public String getStatusCode() {
        return statusCode;
    }


    public String getTimestamp() {
        return timestamp;
    }


    public String getInbound() {
        return inbound;
    }


    public String getOutbound() {
        return outbound;
    }


    public String getTransactionCode() {
        return transactionCode;
    }

    public String getTraceId() {
        return traceId;
    }

    @Override
    public String toString() {
        return "EventHubDTO{" +
                "analyticsTraceSource='" + analyticsTraceSource + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", channelOperationNumber='" + channelOperationNumber + '\'' +
                ", consumerId='" + consumerId + '\'' +
                ", currentDate=" + currentDate +
                ", customerId='" + customerId + '\'' +
                ", region='" + region + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", traceId='" + traceId + '\'' +
                ", inbound='" + inbound + '\'' +
                ", outbound='" + outbound + '\'' +
                ", transactionCode='" + transactionCode + '\'' +
                '}';
    }

    EventHubDTO(Builder b) {
        this.analyticsTraceSource = b.analyticsTraceSource;
        this.applicationId = b.applicationId;
        this.channelOperationNumber = b.channelOperationNumber;
        this.consumerId = b.consumerId;
        this.currentDate = b.currentDate;
        this.customerId = b.customerId;
        this.region = b.region;
        this.statusCode = b.statusCode;
        this.timestamp = b.timestamp;
        this.traceId = b.traceId;
        this.inbound = b.inbound;
        this.outbound = b.outbound;
        this.transactionCode = b.transactionCode;
    }

    public static class Builder{
        private String analyticsTraceSource;
        private String applicationId;
        private String channelOperationNumber;
        private String consumerId;
        private Date currentDate;
        private String customerId;
        private String region;
        private String statusCode;
        private String timestamp;
        private String traceId;
        private String inbound;
        private String outbound;
        private String transactionCode;

        public Builder analyticsTraceSource(String analyticsTraceSource) {
            this.analyticsTraceSource = analyticsTraceSource;
            return this;
        }

        public Builder applicationId(String applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder channelOperationNumber(String channelOperationNumber) {
            this.channelOperationNumber = channelOperationNumber;
            return this;
        }

        public Builder consumerId(String consumerId) {
            this.consumerId = consumerId;
            return this;
        }

        public Builder currentDate(Date currentDate) {
            this.currentDate = currentDate;
            return this;
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder statusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public Builder inbound(String inbound) {
            this.inbound = inbound;
            return this;
        }

        public Builder outbound(String outbound) {
            this.outbound = outbound;
            return this;
        }

        public Builder transactionCode(String transactionCode) {
            this.transactionCode = transactionCode;
            return this;
        }

        public EventHubDTO build() {
            return new EventHubDTO(this);
        }
    }


}
