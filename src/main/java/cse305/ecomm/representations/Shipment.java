package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Shipment {
    @NotNull
    private Integer shipmentId;
    @NotNull
    private Date deliveryDate;
    @NotNull
    private String shipperName;

    public Shipment(Integer shipmentId, Date deliveryDate, String shipperName) {
        this.shipmentId = shipmentId;
        this.deliveryDate = deliveryDate;
        this.shipperName = shipperName;
    }

    public Integer getShipmentId() {
        return shipmentId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }
}
