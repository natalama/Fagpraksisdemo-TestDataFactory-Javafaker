package fagpraksisdemo.model;

import jakarta.validation.constraints.NotNull;

public class Phone {
    @NotNull(message = "Ugyldig telefontype")
    private PhoneType phoneType;
    @NotNull(message = "Nummeret kan ikke være null")
    private String phoneNumber;

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneType=" + phoneType +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
