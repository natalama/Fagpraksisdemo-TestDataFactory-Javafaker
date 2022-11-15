package fagpraksisdemo.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Address {

    @NotNull(message = "Addressetype kreves!")
    private AddressType addressType;
    private Boolean defaultAddress;
    @NotNull(message = "Addresselinje 1 kreves!")
    private String addressLine1;
    @NotNull(message = "Addresselinje 2 kreves!")
    private String addressLine2;
    @NotNull(message = "Addresselinje 3 kreves!")
    private String addressLine3;
    @NotNull(message = "Postkode kreves")
    @Pattern(regexp = "[0-9]{4}", message = "\"${validatedValue}\" er en ugyldig postnummer")
    private String postalCode;
    private String countryCode;

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressType=" + addressType +
                ", defaultAddress=" + defaultAddress +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", addressLine3='" + addressLine3 + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
