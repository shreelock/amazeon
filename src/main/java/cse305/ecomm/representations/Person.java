package cse305.ecomm.representations;

import javax.validation.constraints.NotNull;

public class Person {

    @NotNull
    private Integer personId;
    @NotNull
    private String personName;
    @NotNull
    private String contactNumber;
    @NotNull
    private String emailAddress;
    private Integer age =  0;
    @NotNull
    private String securePassword;
    @NotNull
    private String userName;

    public Person(Integer personId, String personName, String contactNumber, String emailAddress, Integer age, String securePassword, String userName) {
        this.personId = personId;
        this.personName = personName;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.age = age;
        this.securePassword = securePassword;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Integer getAge() {
        return age;
    }

    public String getSecurePassword() {
        return securePassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSecurePassword(String securePassword) {
        this.securePassword = securePassword;
    }
}
