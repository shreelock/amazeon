package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class Customer extends Person {
    @NotNull
    private Integer customerId;

    public Customer(Integer personId, String personName, String contactNumber, String emailAddress, Integer age, String securePassword, Integer customerId) {
        super(personId, personName, contactNumber, emailAddress, age, securePassword);
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
