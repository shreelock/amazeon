package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class Order {
    @NotNull
    private Integer orderId ;
    @NotNull
    private Integer customerId;
    @NotNull
    private Integer paymentId;

    public Order(Integer orderId, Integer customerId, Integer paymentId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.paymentId = paymentId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }
}
