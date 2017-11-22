package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class CustomerInterests {
    @NotNull
    private Integer customerId;
    @NotNull
    private String interest;

    public CustomerInterests(Integer customerId, String interest) {
        this.customerId = customerId;
        this.interest = interest;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getInterest() {
        return interest;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
