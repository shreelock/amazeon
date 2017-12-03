package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class Order {
    @NotNull
    private Integer orderId;
    @NotNull
    private Integer customerId;
    @NotNull
    private Integer paymentId;
    @NotNull
    private OrderDetails orderInfo;

    public Order(Integer orderId, Integer customerId, Integer paymentId, OrderDetails orderInfo) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.orderInfo = orderInfo;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public void setOrderInfo(OrderDetails orderInfo) { this.orderInfo = orderInfo; }

    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public OrderDetails getOrderInfo() { return orderInfo; }
}
