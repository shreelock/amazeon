package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class Payment {
    @NotNull
    private int paymentId;
    @NotNull
    private float amount;
    @NotNull
    private String gateway;
    @NotNull
    private Timestamp payTimestamp;

    public Payment(int paymentId, float amount, String gateway, Timestamp payTimestamp) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.gateway = gateway;
        this.payTimestamp = payTimestamp;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public void setPayTimestamp(Timestamp payTimestamp) {
        this.payTimestamp = payTimestamp;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public float getAmount() {
        return amount;
    }

    public String getGateway() {
        return gateway;
    }

    public Timestamp getPayTimestamp() {
        return payTimestamp;
    }
}
