package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class Address {
    @NotNull
    private Integer personId;
    private String addrType;
    @NotNull
    private String address;

    public Address(Integer personId, String address, String addrType) {
        this.personId = personId;
        this.addrType = addrType;
        this.address = address;
    }

    public Integer getPersonId() {
        return personId;
    }

    public String getAddrType() {
        return addrType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
