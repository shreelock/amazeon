package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class CitiesOfOperation {
    @NotNull
    private Integer sellerId;
    @NotNull
    private String cityName;
    @NotNull
    private Integer zipCode;

    public CitiesOfOperation(Integer sellerId, String cityName, Integer zipCode) {
        this.sellerId = sellerId;
        this.cityName = cityName;
        this.zipCode = zipCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public String getCityName() {
        return cityName;
    }

    public Integer getZipCode() {
        return zipCode;
    }
}
