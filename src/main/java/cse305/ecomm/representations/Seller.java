package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class Seller extends Person {
    @NotNull
    private Integer sellerId;
    private Integer sellerQuality = null;

    public Seller(Integer personId, String personName, String contactNumber, String emailAddress, Integer age, String securePassword, Integer sellerId, Integer sellerQuality) {
        super(personId, personName, contactNumber, emailAddress, age, securePassword);
        this.sellerId = sellerId;
        this.sellerQuality = sellerQuality;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public Integer getSellerQuality() {
        return sellerQuality;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public void setSellerQuality(Integer sellerQuality) {
        this.sellerQuality = sellerQuality;
    }
}
